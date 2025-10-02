package com.hotel_management.hotel_management_service_api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ResponseAddressDto {

    private String addressLine;
    private String city;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String branchId;
}
