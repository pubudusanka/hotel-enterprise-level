package com.hotel_management.hotel_management_service_auth_service.service.Impl;

import com.hotel_management.hotel_management_service_auth_service.config.KeycloakSecurityUtil;
import com.hotel_management.hotel_management_service_auth_service.dto.request.SystemUserRequest;
import com.hotel_management.hotel_management_service_auth_service.entity.Otp;
import com.hotel_management.hotel_management_service_auth_service.entity.SystemUser;
import com.hotel_management.hotel_management_service_auth_service.exception.BadRequestException;
import com.hotel_management.hotel_management_service_auth_service.repository.OtpRepository;
import com.hotel_management.hotel_management_service_auth_service.repository.SystemUserRepository;
import com.hotel_management.hotel_management_service_auth_service.service.SystemUserService;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    @Value("${keycloak.config.realm}")
    private String realm;

    private final SystemUserRepository systemUserRepository;
    private final KeycloakSecurityUtil keycloakSecurityUtil;
    private final OtpRepository otpRepository;

    @Override
    public void createSystemUser(SystemUserRequest data) {
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


    }
}
