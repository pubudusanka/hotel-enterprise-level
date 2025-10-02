package com.hotel_management.hotel_management_service_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String roomId;

    @Column(name = "room_number", length = 80)
    private String roomNumber;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "bed_count", nullable = false)
    private int bedCount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "availability",nullable = false)
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Facility> facilities;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomImage> roomImages;
}
