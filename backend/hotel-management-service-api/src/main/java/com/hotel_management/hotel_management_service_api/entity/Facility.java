package com.hotel_management.hotel_management_service_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "facility")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String facilityId;

    @Column(name = "facility_name")
    private String facilityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
