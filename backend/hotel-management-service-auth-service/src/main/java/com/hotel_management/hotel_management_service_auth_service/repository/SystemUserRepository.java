package com.hotel_management.hotel_management_service_auth_service.repository;

import com.hotel_management.hotel_management_service_auth_service.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, String> {
}
