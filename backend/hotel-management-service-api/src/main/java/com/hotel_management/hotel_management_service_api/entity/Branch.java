package com.hotel_management.hotel_management_service_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String branchId;

    private int roomCount;
    

}
