package com.hotel_management.hotel_management_service_api.dto.response.pagination;

import com.hotel_management.hotel_management_service_api.dto.response.ResponseRoomDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseRoomPaginationDto {

    private List<ResponseRoomDto> dataList;
    private long dataCount;
}
