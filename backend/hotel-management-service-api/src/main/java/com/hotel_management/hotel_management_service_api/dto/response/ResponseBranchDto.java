package com.hotel_management.hotel_management_service_api.dto.response;

import com.hotel_management.hotel_management_service_api.entity.Room;
import com.hotel_management.hotel_management_service_api.enums.BranchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBranchDto {

    private String branchId;
    private String branchName;
    private BranchType branchType;
    private int roomCount;
    private String hotelId;
    private String addressId;

    private List<ResponseRoomDto> rooms;
}
