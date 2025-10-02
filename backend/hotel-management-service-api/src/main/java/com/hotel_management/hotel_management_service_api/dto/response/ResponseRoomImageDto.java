package com.hotel_management.hotel_management_service_api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseRoomImageDto {

    private String roomImageId;
    private String directory;
    private String fileName;
    private String hashCode;
    private String resourceUrl;
    private String roomId;
}
