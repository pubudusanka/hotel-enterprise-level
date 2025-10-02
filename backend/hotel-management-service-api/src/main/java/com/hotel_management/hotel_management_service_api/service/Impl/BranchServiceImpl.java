package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestBranchDto;
import com.hotel_management.hotel_management_service_api.dto.response.*;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseBranchPaginationDto;
import com.hotel_management.hotel_management_service_api.entity.*;
import com.hotel_management.hotel_management_service_api.repository.BranchRepository;
import com.hotel_management.hotel_management_service_api.repository.HotelRepository;
import com.hotel_management.hotel_management_service_api.service.BranchService;
import com.hotel_management.hotel_management_service_api.util.ByteCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final HotelRepository hotelRepository;
    private final ByteCodeHandler byteCodeHandler;

    @Override
    public void createBranch(RequestBranchDto data) throws SQLException {
        branchRepository.save(toBranch(data));
    }

    @Override
    public void updateBranch(RequestBranchDto data, String branchId) throws SQLException {
        Branch selectedBranch = branchRepository.findById(branchId).orElseThrow(
                () -> new RuntimeException("Branch not found by id: " + branchId)
        );
        selectedBranch.setBranchName(data.getBranchName());
        selectedBranch.setBranchType(data.getBranchType());
        selectedBranch.setRoomCount(data.getRoomCount());
        selectedBranch.setHotel(findHotelById(data.getHotelId()));
    }

    @Override
    public void deleteBranch(String branchId) {
        Branch selectedBranch = branchRepository.findById(branchId).orElseThrow(
                () -> new RuntimeException("Branch not found by id: " + branchId)
        );
        branchRepository.delete(selectedBranch);
    }

    @Override
    public ResponseBranchDto findByBranchId(String branchId) throws SQLException {
        Branch selectedBranch = branchRepository.findById(branchId).orElseThrow(
                () -> new RuntimeException("Branch not found by id: " + branchId)
        );
        return toResponseBranchDto(selectedBranch);
    }

    @Override
    public ResponseBranchPaginationDto findBranches(int page, int size, String searchText) {
        return ResponseBranchPaginationDto.builder()
                .dataCount(branchRepository.countSearchBranches(searchText))
                .dataList(
                        branchRepository.searchAllBranches(searchText, PageRequest.of(page,size))
                                .stream().map(branch -> {
                                    try {
                                        return toResponseBranchDto(branch);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }).toList()
                )
                .build();
    }

    @Override
    public ResponseBranchPaginationDto findBranchesByHotelId(int page, int size, String hotelId) {
        return ResponseBranchPaginationDto.builder()
                .dataCount(branchRepository.countSearchBranchesByHotelId(hotelId))
                .dataList(
                        branchRepository.findBranchByHotelId(hotelId, PageRequest.of(page,size))
                                .stream().map(branch -> {
                                    try {
                                        return toResponseBranchDto(branch);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }).toList()
                )
                .build();
    }

    //dto to entity
    private Branch toBranch(RequestBranchDto data) throws SQLException {
        return data == null ? null :
                Branch.builder()
                        .branchName(data.getBranchName())
                        .branchType(data.getBranchType())
                        .roomCount(data.getRoomCount())
                        .hotel(findHotelById(data.getHotelId()))
                        .build();
    }

    //find hotel related to the branch
    private Hotel findHotelById(String hotelId) throws SQLException {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    // response branches from entity to dto
    private ResponseBranchDto toResponseBranchDto(Branch branch) throws SQLException {
        return branch == null ? null :
                ResponseBranchDto.builder()
                        .branchId(branch.getBranchId())
                        .branchName(branch.getBranchName())
                        .roomCount(branch.getRoomCount())
                        .branchType(branch.getBranchType())
                        .hotelId(branch.getHotel().getHotelId())
                        .addressId(branch.getAddress().getAddressId())
                        .rooms(
                                branch.getRooms().stream().map(room -> {
                                    try {
                                        return toResponseRoomDto(room);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                } ).toList()
                        )
                        .build();
    }

    // rooms
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
