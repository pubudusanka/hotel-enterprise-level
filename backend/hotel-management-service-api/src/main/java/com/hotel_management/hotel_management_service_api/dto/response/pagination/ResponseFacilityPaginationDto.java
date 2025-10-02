package com.hotel_management.hotel_management_service_api.dto.response.pagination;

import com.hotel_management.hotel_management_service_api.dto.response.ResponseFacilityDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseFacilityPaginationDto {

    private List<ResponseFacilityDto> dataList;
    private long dataCount;
}
