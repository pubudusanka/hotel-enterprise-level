package com.hotel_management.hotel_management_service_api.api;

import com.hotel_management.hotel_management_service_api.dto.request.RequestHotelDto;
import com.hotel_management.hotel_management_service_api.service.HotelService;
import com.hotel_management.hotel_management_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                        201, "Hotel Saved Successfully!", data
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/visitor/find-by-id/{id}")
    public ResponseEntity<StandardResponseDto> findHotelById(@PathVariable("id") String hotelId) throws SQLException {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Hotel Found!", hotelService.findByHotelId(hotelId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-all")
    public ResponseEntity<StandardResponseDto> findAllHotels(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Hotels!", hotelService.findHotels(page,size,searchText)
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/host/delete/{id}")
    public ResponseEntity<StandardResponseDto> deleteHotel(@PathVariable("id") String hotelId) {
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204, "Hotel Deleted Successfully!", null
                ),
                HttpStatus.NO_CONTENT
        );
    }

    @PostMapping("/admin/update/{id}")
    public ResponseEntity<StandardResponseDto> updateHotel(
            @PathVariable("id") String hotelId,
            @RequestBody RequestHotelDto data) throws SQLException {
        hotelService.updateHotel(data, hotelId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Hotel updated successfully", null
                ),
                HttpStatus.CREATED
        );
    }
}
