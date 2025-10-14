package com.hotel_management.hotel_management_service_auth_service.service;

import com.hotel_management.hotel_management_service_auth_service.dto.request.PasswordRequest;
import com.hotel_management.hotel_management_service_auth_service.dto.request.SystemUserRequest;

import java.util.ArrayList;
import java.util.List;

public interface SystemUserService {

    public void createSystemUser(SystemUserRequest data);
    public void initializeHost(List<SystemUserRequest> users);
    public void resend (String email, String type);
    public void forgotPasswordSendVerificationCode(String email);
    public boolean verifyReset(String otp,String email);
    public boolean passwordReset(PasswordRequest data);
}
