package com.hotel_management.hotel_management_service_auth_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "otp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "property_id", nullable = false)
    private String propertyId;

    @Column(name = "code", nullable = false, length = 80)
    private String code;

    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "is_verified")
    private Boolean isVerified;
    @Column(name = "attempts")
    private Integer attempts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "system_user_id", nullable = false, unique = true)
    private SystemUser systemUser;
}
