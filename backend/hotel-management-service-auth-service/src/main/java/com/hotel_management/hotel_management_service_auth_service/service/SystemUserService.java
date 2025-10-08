package com.hotel_management.hotel_management_service_auth_service.service;

import com.hotel_management.hotel_management_service_auth_service.dto.request.SystemUserRequest;

public interface SystemUserService {

    public void createSystemUser(SystemUserRequest data);
}
