package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestHotelDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseBranchDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseHotelDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseHotelPaginationDto;
import com.hotel_management.hotel_management_service_api.entity.Branch;
import com.hotel_management.hotel_management_service_api.entity.Hotel;
import com.hotel_management.hotel_management_service_api.service.HotelService;
import com.hotel_management.hotel_management_service_api.util.ByteCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final ByteCodeHandler byteCodeHandler;

    @Override
    public void createHotel(RequestHotelDto dto) {

    }

    @Override
    public void updateHotel(RequestHotelDto dto, String hotelId) {

    }

    @Override
    public void deleteHotel(String hotelId) {

    }

    @Override
    public ResponseHotelDto findByHotelId(String hotelId) {
        return null;
    }

    @Override
    public ResponseHotelPaginationDto findHotels(int page, int size, String searchText) {
        return null;
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
                        .build();
    }
}
