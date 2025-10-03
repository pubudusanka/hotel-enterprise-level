package com.hotel_management.hotel_management_service_api.api;

import com.hotel_management.hotel_management_service_api.dto.request.RequestFacilityDto;
import com.hotel_management.hotel_management_service_api.service.FacilityService;
import com.hotel_management.hotel_management_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel-service/api/v1/facility")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @PostMapping("/user/create")
    public ResponseEntity<StandardResponseDto> createFacility(@RequestBody RequestFacilityDto requestFacilityDto) {
        facilityService.createFacility(requestFacilityDto);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Facility Creation Successfully!", null
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/admin/update/{id}")
    public ResponseEntity<StandardResponseDto> updateFacility(
            @PathVariable("id") String facilityId,
            @RequestBody RequestFacilityDto data) {
        facilityService.updateFacility(data, facilityId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Facility updated successfully", null
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/host/delete/{id}")
    public ResponseEntity<StandardResponseDto> deleteFacility(@PathVariable("id") String facilityId) {
        facilityService.deleteFacility(facilityId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204, "Facility Deleted Successfully!", null
                ),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/visitor/find-by-id/{id}")
    public ResponseEntity<StandardResponseDto> findFacilityById(@PathVariable("id") String facilityId) {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Facility Found!", facilityService.findFacilityByFacilityId(facilityId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-rooms-by-facility-id")
    public ResponseEntity<StandardResponseDto> findAllRoomsByFacilityId(
            @RequestParam String facilityId,
            @RequestParam int page,
            @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Rooms!", facilityService.findRoomsByFacilityId(page,size,facilityId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-rooms-by-facility-name")
    public ResponseEntity<StandardResponseDto> findAllRoomsByFacilityName(
            @RequestParam String facilityName,
            @RequestParam int page,
            @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Rooms!", facilityService.findRoomsByFacilityName(page,size,facilityName)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-all-facilities")
    public ResponseEntity<StandardResponseDto> findAllFacilities(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Facilities!", facilityService.findAllFacilities(page,size,searchText)
                ),
                HttpStatus.OK
        );
    }

}
