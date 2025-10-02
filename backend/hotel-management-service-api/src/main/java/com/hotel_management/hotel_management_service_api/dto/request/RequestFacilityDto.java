package com.hotel_management.hotel_management_service_api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestFacilityDto {

    private String facilityName;
    private String roomId;
}
