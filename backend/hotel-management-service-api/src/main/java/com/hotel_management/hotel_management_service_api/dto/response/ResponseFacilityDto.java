package com.hotel_management.hotel_management_service_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseFacilityDto {

    private String facilityId;
    private String facilityName;
    private String roomId;
}
