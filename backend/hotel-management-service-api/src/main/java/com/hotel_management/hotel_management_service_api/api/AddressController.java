package com.hotel_management.hotel_management_service_api.api;

import com.hotel_management.hotel_management_service_api.dto.request.RequestAddressDto;
import com.hotel_management.hotel_management_service_api.dto.request.RequestBranchDto;
import com.hotel_management.hotel_management_service_api.service.AddressService;
import com.hotel_management.hotel_management_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/hotel-service/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/user/create")
    public ResponseEntity<StandardResponseDto> createAddress(@RequestBody RequestAddressDto addressDto) throws SQLException {
        addressService.createAddress(addressDto);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Address Creation Successfully!", null
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/admin/update/{id}")
    public ResponseEntity<StandardResponseDto> updateAddress(
            @PathVariable("id") String addressId,
            @RequestBody RequestAddressDto data) throws SQLException {
        addressService.updateAddress(data, addressId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Address updated successfully", null
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/host/delete/{id}")
    public ResponseEntity<StandardResponseDto> deleteAddress(@PathVariable("id") String addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204, "Address Deleted Successfully!", null
                ),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/visitor/find-by-id/{id}")
    public ResponseEntity<StandardResponseDto> findAddressById(@PathVariable("id") String addressId) throws SQLException {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Address Found!", addressService.findById(addressId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/host/find-by-branch-id/{id}")
    public ResponseEntity<StandardResponseDto> findAddressByBranchId(@PathVariable("id") String branchId) throws SQLException {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Address Found!", addressService.findByBranchId(branchId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-by-city-name")
    public ResponseEntity<StandardResponseDto> findAddressByCityName(
            @RequestParam String city,
            @RequestParam int page,
            @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Branches!", addressService.findAddressByCity(page,size,city)
                ),
                HttpStatus.OK
        );
    }

}
