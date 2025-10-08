package com.hotel_management.hotel_management_service_auth_service.repository;

import com.hotel_management.hotel_management_service_auth_service.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {

    @Query(value = "SELECT * FROM otp WHERE system_user_id LIKE CONCAT('%', :systemUserId, '%')", nativeQuery = true)
    public Optional<Otp> findBySystemUserId(String systemUserId);
}
