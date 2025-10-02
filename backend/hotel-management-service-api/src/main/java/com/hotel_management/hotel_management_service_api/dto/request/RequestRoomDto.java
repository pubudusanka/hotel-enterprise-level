package com.hotel_management.hotel_management_service_api.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestRoomDto {

    private int bedCount;
    private boolean isAvailable;
    private BigDecimal price;
    private String roomNumber;
    private String roomType;
    private String branchId;
}
