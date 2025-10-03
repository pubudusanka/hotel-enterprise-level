package com.hotel_management.hotel_management_service_api.service;

import com.hotel_management.hotel_management_service_api.dto.request.RequestRoomImageDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseRoomImageDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseRoomImagePaginationDto;

public interface RoomImageService {
    public void createRoomImage(RequestRoomImageDto data);
    public void updateRoomImage(RequestRoomImageDto data, String roomImageId);
    public void deleteRoomImage(String roomImageId);
    public ResponseRoomImageDto findRoomImageById(String roomImageId);
    public ResponseRoomImagePaginationDto findRoomImagesByRoomId(int page, int size, String roomId);
}
