package com.hotel_management.hotel_management_service_api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseFacilityDto {

    private String facilityId;
    private String facilityName;
    private String roomId;
}
