package com.hotel_management.hotel_management_service_auth_service.service;

import java.io.IOException;

public interface EmailService {
    public boolean sendUserSignUpVerificationCode(String toEmail, String subject, String otp, String firstName);
    public boolean sendHostPassword(String toEmail, String subject, String password, String firstName);
}
