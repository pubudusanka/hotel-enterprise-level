package com.hotel_management.hotel_management_service_api.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestHotelDto {

    private String hotelName;
    private int starRating;
    private String description;
    private BigDecimal startingFrom;
}
