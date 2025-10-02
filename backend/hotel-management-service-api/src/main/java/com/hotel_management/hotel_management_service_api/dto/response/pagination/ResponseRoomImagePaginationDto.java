package com.hotel_management.hotel_management_service_api.dto.response.pagination;

import com.hotel_management.hotel_management_service_api.dto.response.ResponseRoomImageDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseRoomImagePaginationDto {

    private List<ResponseRoomImageDto> dataList;
    private long dataCount;
}
