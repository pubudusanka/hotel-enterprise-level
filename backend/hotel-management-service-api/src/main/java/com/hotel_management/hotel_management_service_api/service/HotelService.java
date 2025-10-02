package com.hotel_management.hotel_management_service_api.service;

import com.hotel_management.hotel_management_service_api.dto.request.RequestHotelDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseHotelDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseHotelPaginationDto;

import java.sql.SQLException;

public interface HotelService {

    public void createHotel(RequestHotelDto dto) throws SQLException;
    public void updateHotel(RequestHotelDto dto, String hotelId) throws SQLException;
    public void deleteHotel(String hotelId);
    public ResponseHotelDto findByHotelId(String hotelId) throws SQLException;
    public ResponseHotelPaginationDto findHotels(int page, int size, String searchText);
}
