package com.hotel_management.hotel_management_service_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestFacilityDto {

    private String facilityName;
    private String roomId;
}
