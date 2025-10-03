package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestRoomDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseFacilityDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseRoomDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseRoomImageDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseRoomPaginationDto;
import com.hotel_management.hotel_management_service_api.entity.Branch;
import com.hotel_management.hotel_management_service_api.entity.Facility;
import com.hotel_management.hotel_management_service_api.entity.Room;
import com.hotel_management.hotel_management_service_api.entity.RoomImage;
import com.hotel_management.hotel_management_service_api.repository.BranchRepository;
import com.hotel_management.hotel_management_service_api.repository.RoomRepository;
import com.hotel_management.hotel_management_service_api.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final BranchRepository branchRepository;

    @Override
    public void createRoom(RequestRoomDto data) {
        roomRepository.save(toRoom(data));
    }

    @Override
    public void updateRoom(RequestRoomDto data, String roomId) {
        Room selectedRoom = roomRepository.findById(roomId).orElseThrow(
                () -> new RuntimeException("Room not found with id: " + roomId)
        );
        selectedRoom.setRoomNumber(data.getRoomNumber());
        selectedRoom.setRoomType(data.getRoomType());
        selectedRoom.setBedCount(data.getBedCount());
        selectedRoom.setPrice(data.getPrice());
        selectedRoom.setAvailable(data.getIsAvailable());
        selectedRoom.setBranch(findBranchById(data.getBranchId()));
        roomRepository.save(selectedRoom);
    }

    @Override
    public void deleteRoom(String roomId) {
        Room selectedRoom = roomRepository.findById(roomId).orElseThrow(
                () -> new RuntimeException("Room not found with id: " + roomId)
        );
        roomRepository.delete(selectedRoom);
    }

    @Override
    public ResponseRoomDto findRoomById(String roomId) throws SQLException {
        Room selectedRoom = roomRepository.findById(roomId).orElseThrow(
                () -> new RuntimeException("Room not found with id: " + roomId)
        );
        return toResponseRoomDto(selectedRoom);
    }

    @Override
    public ResponseRoomPaginationDto findRoomsInBranchId(int page, int size, String branchId) {
        return ResponseRoomPaginationDto.builder()
                .dataCount(roomRepository.countSearchRoomByBranchId(branchId))
                .dataList(
                        roomRepository.findRoomByBranchId(branchId, PageRequest.of(page, size))
                                .stream().map(room -> {
                                    try {
                                        return toResponseRoomDto(room);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }).toList()
                )
                .build();
    }

    @Override
    public ResponseRoomPaginationDto findRoomsInBranchName(int page, int size, String branchName) {
        Branch selectedBranch = branchRepository.findBranchByName(branchName);
        String branchId = selectedBranch.getBranchId();

        return ResponseRoomPaginationDto.builder()
                .dataCount(roomRepository.countSearchRoomByBranchId(branchId))
                .dataList(
                        roomRepository.findRoomByBranchId(branchId, PageRequest.of(page, size))
                                .stream().map(room -> {
                                    try {
                                        return toResponseRoomDto(room);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }).toList()
                )
                .build();
    }

    // dto mapped to the entity
    private Room toRoom(RequestRoomDto data){
        return data== null ? null :
                Room.builder()
                        .roomNumber(data.getRoomNumber())
                        .roomType(data.getRoomType())
                        .bedCount(data.getBedCount())
                        .price(data.getPrice())
                        .isAvailable(data.getIsAvailable())
                        .branch(findBranchById(data.getBranchId()))
                        .build();
    }

    // find branch related to the hotel
    private Branch findBranchById(String branchId){
        return branchRepository.findById(branchId).orElse(null);
    }

    // response room (entity to dto)
    private ResponseRoomDto toResponseRoomDto(Room room) throws SQLException {
        return room == null ? null :
                ResponseRoomDto.builder()
                        .roomId(room.getRoomId())
                        .bedCount(room.getBedCount())
                        .isAvailable(room.isAvailable())
                        .price(room.getPrice())
                        .roomNumber(room.getRoomNumber())
                        .roomType(room.getRoomType())
                        .branchId(room.getBranch().getBranchId())
                        .facilities(
                                room.getFacilities().stream().map(facility -> {
                                    try {
                                        return toResponseFacilityDto(facility);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }).toList()
                        )
                        .images(
                                room.getRoomImages().stream().map(roomImage -> {
                                    try {
                                        return toResponseRoomImageDto(roomImage);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }).toList()
                        )
                        .build();
    }

    // facility
    private ResponseFacilityDto toResponseFacilityDto(Facility facility) throws SQLException {
        return facility == null ? null :
                ResponseFacilityDto.builder()
                        .facilityId(facility.getFacilityId())
                        .facilityName(facility.getFacilityName())
                        .roomId(facility.getRoom().getRoomId())
                        .build();
    }

    // room image
    private ResponseRoomImageDto toResponseRoomImageDto(RoomImage roomImage) throws SQLException {
        return roomImage == null ? null :
                ResponseRoomImageDto.builder()
                        .roomImageId(roomImage.getRoomImageId())
                        .directory(Arrays.toString(roomImage.getFileFormatter().getDirectory()))
                        .fileName(Arrays.toString(roomImage.getFileFormatter().getFileName()))
                        .hashCode(Arrays.toString(roomImage.getFileFormatter().getHash()))
                        .resourceUrl(Arrays.toString(roomImage.getFileFormatter().getResourceUrl()))
                        .roomId(roomImage.getRoom().getRoomId())
                        .build();
    }
}
