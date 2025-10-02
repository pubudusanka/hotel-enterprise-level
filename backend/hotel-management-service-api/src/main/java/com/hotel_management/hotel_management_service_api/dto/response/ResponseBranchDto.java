package com.hotel_management.hotel_management_service_api.dto.response;

import com.hotel_management.hotel_management_service_api.enums.BranchType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBranchDto {

    private String branchId;
    private String branchName;
    private BranchType branchType;
    private int roomCount;
    private String hotelId;
}
