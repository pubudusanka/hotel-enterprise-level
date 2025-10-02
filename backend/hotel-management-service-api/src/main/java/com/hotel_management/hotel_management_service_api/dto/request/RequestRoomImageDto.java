package com.hotel_management.hotel_management_service_api.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestRoomImageDto {
    private MultipartFile file;
    private String roomId;
}
