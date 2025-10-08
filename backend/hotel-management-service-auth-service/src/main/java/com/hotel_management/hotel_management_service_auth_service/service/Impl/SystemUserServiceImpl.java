package com.hotel_management.hotel_management_service_auth_service.service.Impl;

import com.hotel_management.hotel_management_service_auth_service.dto.request.SystemUserRequest;
import com.hotel_management.hotel_management_service_auth_service.exception.BadRequestException;
import com.hotel_management.hotel_management_service_auth_service.repository.SystemUserRepository;
import com.hotel_management.hotel_management_service_auth_service.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository systemUserRepository;

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
    }
}
