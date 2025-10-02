package com.hotel_management.hotel_management_service_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String roomImageId;

    @Embedded
    private FileFormatter fileFormatter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

}
