package com.hotel_management.hotel_management_service_api.dto.request;

import com.hotel_management.hotel_management_service_api.enums.BranchType;
import lombok.Data;

@Data
public class RequestBranchDto {

    private String branchName;
    private BranchType branchType;
    private int roomCount;
    private String hotelId;
}
