package com.hotel_management.hotel_management_service_auth_service.repository;

import com.hotel_management.hotel_management_service_auth_service.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, String> {

    public Optional<SystemUser> findByEmail(String email);
}
