package com.hotel_management.hotel_management_service_api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ResponseRoomDto {

    private String roomId;
    private int bedCount;
    private boolean isAvailable;
    private BigDecimal price;
    private String roomNumber;
    private String roomType;
    private String branchId;

    private List<ResponseFacilityDto> facilities;
    private List<ResponseRoomImageDto> images;
}
