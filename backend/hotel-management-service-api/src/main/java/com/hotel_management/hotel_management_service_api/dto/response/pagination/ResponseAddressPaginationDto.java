package com.hotel_management.hotel_management_service_api.dto.response.pagination;

import com.hotel_management.hotel_management_service_api.dto.response.ResponseAddressDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseAddressPaginationDto {

    private List<ResponseAddressDto> dataList;
    private long dataCount;
}
