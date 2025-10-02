package com.hotel_management.hotel_management_service_api.api;

import com.hotel_management.hotel_management_service_api.dto.request.RequestHotelDto;
import com.hotel_management.hotel_management_service_api.service.HotelService;
import com.hotel_management.hotel_management_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel-service/api/v1/hotels")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/user/create")
    public ResponseEntity<StandardResponseDto> createHotel(@RequestBody RequestHotelDto data) throws SQLException {
        hotelService.createHotel(data);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Hotel Saved Successfully!", null
                ),
                HttpStatus.CREATED
        );
    }
}
