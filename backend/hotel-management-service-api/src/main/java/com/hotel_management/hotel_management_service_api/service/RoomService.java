package com.hotel_management.hotel_management_service_api.service;

import com.hotel_management.hotel_management_service_api.dto.request.RequestRoomDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseRoomDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseRoomPaginationDto;

import java.sql.SQLException;

public interface RoomService {

    public void createRoom(RequestRoomDto data);
    public void updateRoom(RequestRoomDto data, String roomId);
    public void deleteRoom(String roomId);
    public ResponseRoomDto findRoomById(String roomId) throws SQLException;
    public ResponseRoomPaginationDto findRoomsInBranchId(int page, int size, String branchId);
    public ResponseRoomPaginationDto findRoomsInBranchName(int page, int size, String branchName);
}
