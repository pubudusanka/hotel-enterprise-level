package com.hotel_management.hotel_management_service_auth_service.service.Impl;

import com.hotel_management.hotel_management_service_auth_service.config.KeycloakSecurityUtil;
import com.hotel_management.hotel_management_service_auth_service.dto.request.SystemUserRequest;
import com.hotel_management.hotel_management_service_auth_service.entity.Otp;
import com.hotel_management.hotel_management_service_auth_service.entity.SystemUser;
import com.hotel_management.hotel_management_service_auth_service.exception.BadRequestException;
import com.hotel_management.hotel_management_service_auth_service.repository.OtpRepository;
import com.hotel_management.hotel_management_service_auth_service.repository.SystemUserRepository;
import com.hotel_management.hotel_management_service_auth_service.service.EmailService;
import com.hotel_management.hotel_management_service_auth_service.service.SystemUserService;
import com.hotel_management.hotel_management_service_auth_service.util.OtpGenerator;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    @Value("${keycloak.config.realm}")
    private String realm;

    private final SystemUserRepository systemUserRepository;
    private final KeycloakSecurityUtil keycloakSecurityUtil;
    private final OtpRepository otpRepository;
    private final OtpGenerator otpGenerator;
    private final EmailService emailService;

    @Override
    public void createSystemUser(SystemUserRequest data) {

        // find the user into the keycloak db and system db

        if (data.getFirstName()==null || data.getFirstName().trim().isEmpty()) {
            throw new BadRequestException("First name is required");
        }
        if (data.getLastName()==null || data.getLastName().trim().isEmpty()) {
            throw new BadRequestException("Last name is required");
        }
        if (data.getEmail()==null || data.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Email is required");
        }

        String userId="";
        String otp="";
        Keycloak keycloak = null;

        UserRepresentation existingUser = null;
        keycloak = keycloakSecurityUtil.getKeycloakInstance();

        existingUser = keycloak.realm(realm).users().search(data.getEmail()).stream()
                .findFirst().orElse(null);

        if (existingUser != null) {
            Optional<SystemUser> selectedSystemUserFromAuthService =
                    systemUserRepository.findByEmail(existingUser.getEmail());

            if (selectedSystemUserFromAuthService.isEmpty()) {
                keycloak.realm(realm).users().delete(existingUser.getId());
            }else{
                throw new DuplicateRequestException("Email already exists!");
            }
        }else{
            Optional<SystemUser> selectedSystemUserFromAuthService =
                    systemUserRepository.findByEmail(data.getEmail());

            if (selectedSystemUserFromAuthService.isPresent()) {
                Optional<Otp> selectedOtp = otpRepository.findBySystemUserId(selectedSystemUserFromAuthService.get().getUserId());
                if (selectedOtp.isPresent()) {
                    otpRepository.deleteById(selectedOtp.get().getPropertyId());
                }
                systemUserRepository.deleteById(selectedSystemUserFromAuthService.get().getUserId());
            }
        }

        //=================end of the function===============

        UserRepresentation userRepresentation = mapUserRepo(data, false,false);
        // send data to keycloak server
        Response response = keycloak.realm(realm).users().create(userRepresentation);
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            // attach roles
            RoleRepresentation userRole = keycloak.realm(realm).roles().get("user").toRepresentation();
            userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(userRole));

            //created final user caught
            UserRepresentation createdUser = keycloak.realm(realm).users().get(userId).toRepresentation();

            // save to systemuser db
            SystemUser systemUser = SystemUser.builder()
                    .userId(userId)
                    .keycloakId(createdUser.getId())
                    .firstName(data.getFirstName())
                    .lastName(data.getLastName())
                    .email(data.getEmail())
                    .contactNo(data.getContact())
                    .isActive(false)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(false)
                    .isEmailVerified(false)
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();

            SystemUser savedUser = systemUserRepository.save(systemUser);

            // generate otp
            Otp createdOtp = Otp.builder()
                    .code(otpGenerator.generateOtp(5))
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .isVerified(false)
                    .attempts(0)
                    .build();

            otpRepository.save(createdOtp);

            //send otp to the email
            emailService.sendUserSignUpVerificationCode(data.getEmail(),"Verify your Email", data.getFirstName(),
                    createdOtp.getCode());

        }

    }


    // create user to keycloak
    private UserRepresentation mapUserRepo(SystemUserRequest data, boolean isEmailVerified, boolean isEnabled){
        UserRepresentation user = new UserRepresentation();
        user.setEmail(data.getEmail());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setUsername(data.getEmail());

        user.setEnabled(isEnabled);
        user.setEmailVerified(isEmailVerified);

        List<CredentialRepresentation> credentialList = new ArrayList<>();
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setValue(data.getPassword());
        credentialList.add(credential);
        user.setCredentials(credentialList);

        return user;
    }


    @Override
    public void initializeHost(List<SystemUserRequest> users) {
        for (SystemUserRequest data : users) {
            Optional<SystemUser> selectedUser = systemUserRepository.findByEmail(data.getEmail());

            if (selectedUser.isPresent()) {
                continue;
            }

            String userId="";
            String otp="";
            Keycloak keycloak = null;

            UserRepresentation existingUser = null;
            keycloak = keycloakSecurityUtil.getKeycloakInstance();

            existingUser = keycloak.realm(realm).users().search(data.getEmail()).stream()
                    .findFirst().orElse(null);

            if (existingUser != null) {
                Optional<SystemUser> selectedSystemUserFromAuthService =
                        systemUserRepository.findByEmail(existingUser.getEmail());

                if (selectedSystemUserFromAuthService.isEmpty()) {
                    keycloak.realm(realm).users().delete(existingUser.getId());
                }else{
                    throw new DuplicateRequestException("Email already exists!");
                }
            }else{
                Optional<SystemUser> selectedSystemUserFromAuthService =
                        systemUserRepository.findByEmail(data.getEmail());

                if (selectedSystemUserFromAuthService.isPresent()) {
                    Optional<Otp> selectedOtp = otpRepository.findBySystemUserId(selectedSystemUserFromAuthService.get().getUserId());
                    if (selectedOtp.isPresent()) {
                        otpRepository.deleteById(selectedOtp.get().getPropertyId());
                    }
                    systemUserRepository.deleteById(selectedSystemUserFromAuthService.get().getUserId());
                }
            }

            //=================end of the function===============

            UserRepresentation userRepresentation = mapUserRepo(data, true, true);
            // send data to keycloak server
            Response response = keycloak.realm(realm).users().create(userRepresentation);
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                // attach roles
                RoleRepresentation userRole = keycloak.realm(realm).roles().get("host").toRepresentation();
                userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(userRole));

                //created final user caught
                UserRepresentation createdUser = keycloak.realm(realm).users().get(userId).toRepresentation();

                // save to systemuser db
                SystemUser systemUser = SystemUser.builder()
                        .userId(userId)
                        .keycloakId(createdUser.getId())
                        .firstName(data.getFirstName())
                        .lastName(data.getLastName())
                        .email(data.getEmail())
                        .contactNo(data.getContact())
                        .isActive(true)
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .isEmailVerified(true)
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .build();

                SystemUser savedUser = systemUserRepository.save(systemUser);


                //send password to the email
                emailService.sendHostPassword(data.getEmail(), "Access System by using above Password", data.getFirstName(),
                        data.getPassword());
            }
        }
    }


}
