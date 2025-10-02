package com.hotel_management.hotel_management_service_api.service.Impl;

import com.hotel_management.hotel_management_service_api.dto.request.RequestHotelDto;
import com.hotel_management.hotel_management_service_api.dto.response.ResponseHotelDto;
import com.hotel_management.hotel_management_service_api.dto.response.pagination.ResponseHotelPaginationDto;
import com.hotel_management.hotel_management_service_api.service.HotelService;

public class HotelServiceImpl implements HotelService {
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
}
