package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestHotelDto;
import com.hotel_management.hotel_management_service_api.dto.response.*;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseHotelPaginationDto;
import com.hotel_management.hotel_management_service_api.entity.*;
import com.hotel_management.hotel_management_service_api.exception.EntryNotFoundException;
import com.hotel_management.hotel_management_service_api.repository.HotelRepository;
import com.hotel_management.hotel_management_service_api.service.HotelService;
import com.hotel_management.hotel_management_service_api.util.ByteCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final ByteCodeHandler byteCodeHandler;
    private final HotelRepository hotelRepository;

    @Override
    public void createHotel(RequestHotelDto dto) throws SQLException {
        hotelRepository.save(toHotel(dto));
    }

    @Override
    public void updateHotel(RequestHotelDto dto, String hotelId) throws SQLException {
        Hotel selectedHotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new EntryNotFoundException("Hotel not found with id: " + hotelId));
        selectedHotel.setHotelName(dto.getHotelName());
        selectedHotel.setDescription(byteCodeHandler.stringToBlob(dto.getDescription()));
        selectedHotel.setStarRating(dto.getStarRating());
        selectedHotel.setStartingFrom(dto.getStartingFrom());
        selectedHotel.setUpdatedAt(LocalDateTime.now());
        hotelRepository.save(selectedHotel);
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotel selectedHotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new EntryNotFoundException("Hotel not found with id: " + hotelId));
        hotelRepository.delete(selectedHotel);
    }

    @Override
    public ResponseHotelDto findByHotelId(String hotelId) throws SQLException {
        Hotel selectedHotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new EntryNotFoundException("Hotel not found with id: " + hotelId));
        return toResponseHotelDto(selectedHotel);
    }

    @Override
    public ResponseHotelPaginationDto findHotels(int page, int size, String searchText) {
        return ResponseHotelPaginationDto.builder()
                .dataCount(hotelRepository.countSearchHotels(searchText))
                .dataList(
                        hotelRepository.searchAllHotels(searchText, PageRequest.of(page,size))
                                .stream().map(hotel -> {
                                    try {
                                        return toResponseHotelDto(hotel);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }).toList()
                )
                .build();
    }

    //dto mapped to the entity
    private Hotel toHotel(RequestHotelDto dto) throws SQLException {
        return dto == null ? null:
                Hotel.builder()
                        .hotelName(dto.getHotelName())
                        .starRating(dto.getStarRating())
                        .description(byteCodeHandler.stringToBlob(dto.getDescription()))
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .activeStatus(true)
                        .startingFrom(dto.getStartingFrom())
                        .build();
    }

    //entity mapped to the dto
    private ResponseHotelDto toResponseHotelDto(Hotel hotel) throws SQLException {
        return hotel == null ? null :
                ResponseHotelDto.builder()
                        .hotelId(hotel.getHotelId())
                        .hotelName(hotel.getHotelName())
                        .starRating(hotel.getStarRating())
                        .activeStatus(hotel.isActiveStatus())
                        .startingFrom(hotel.getStartingFrom())
                        .updatedAt(LocalDateTime.now())
                        .createdAt(hotel.getCreatedAt())
                        .description(byteCodeHandler.blobToString(hotel.getDescription()))
                        .branches(
                                hotel.getBranches().stream().map(branch -> {
                                    try {
                                        return toResponseBranchDto(branch);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                } ).toList()
                        )
                        .build();
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
