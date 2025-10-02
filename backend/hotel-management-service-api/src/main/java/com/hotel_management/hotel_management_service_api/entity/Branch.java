package com.hotel_management.hotel_management_service_api.entity;

import com.hotel_management.hotel_management_service_api.enums.BranchType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "branch")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String branchId;

    @Column(name = "room_count", nullable = false)
    private int roomCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch_type", nullable = false)
    private BranchType branchType;

    @Column(name = "branch_name", nullable = false, length = 150)
    private String branchName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @OneToOne(mappedBy = "branch", cascade = CascadeType.ALL)
    private Address address;

}
