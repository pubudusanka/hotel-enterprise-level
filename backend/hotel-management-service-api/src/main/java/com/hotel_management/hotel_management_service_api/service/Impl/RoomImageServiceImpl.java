package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestRoomImageDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseRoomImageDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseRoomImagePaginationDto;
import com.hotel_management.hotel_management_service_api.service.RoomImageService;
import com.hotel_management.hotel_management_service_api.util.ByteCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomImageServiceImpl implements RoomImageService {

    private final ByteCodeHandler byteCodeHandler;

    @Override
    public void createRoomImage(RequestRoomImageDto data) {

    }

    @Override
    public void updateRoomImage(RequestRoomImageDto data, String roomImageId) {

    }

    @Override
    public void deleteRoomImage(String roomImageId) {

    }

    @Override
    public ResponseRoomImageDto findRoomImageById(String roomImageId) {
        return null;
    }

    @Override
    public ResponseRoomImagePaginationDto findRoomImagesByRoomId(int page, int size, String roomId) {
        return null;
    }

}
