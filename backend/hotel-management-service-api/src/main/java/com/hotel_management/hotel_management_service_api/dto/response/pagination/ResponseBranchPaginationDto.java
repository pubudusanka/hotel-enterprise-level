package com.hotel_management.hotel_management_service_api.dto.response.pagination;

import com.hotel_management.hotel_management_service_api.dto.response.ResponseBranchDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseBranchPaginationDto {

    private List<ResponseBranchDto> dataList;
    private long dataCount;
}
