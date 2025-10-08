package com.hotel_management.hotel_management_service_auth_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "system_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private String userId;
    
    @Column(name = "keycloak_id", nullable = false)
    private String keycloakId;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false,length = 100)
    private String lastName;
    @Column(name = "email", nullable = false ,length = 100)
    private String email;
    @Column(name = "contact_no", length = 20)
    private String contactNo;

    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_account_non_expired")
    private Boolean isAccountNonExpired;
    @Column(name = "is_account_non_locked")
    private Boolean isAccountNonLocked;
    @Column(name = "is_credentials_non_expired")
    private Boolean isCredentialsNonExpired;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
}
