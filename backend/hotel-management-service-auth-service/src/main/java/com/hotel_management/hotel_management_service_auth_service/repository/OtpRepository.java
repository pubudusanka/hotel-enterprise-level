package com.hotel_management.hotel_management_service_auth_service.repository;

import com.hotel_management.hotel_management_service_auth_service.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, String> {
}
