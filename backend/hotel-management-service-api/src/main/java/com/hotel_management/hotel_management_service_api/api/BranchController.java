package com.hotel_management.hotel_management_service_api.api;

import com.hotel_management.hotel_management_service_api.dto.request.RequestBranchDto;
import com.hotel_management.hotel_management_service_api.service.BranchService;
import com.hotel_management.hotel_management_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/hotel-service/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/user/create")
    public ResponseEntity<StandardResponseDto> createBranch(@RequestBody RequestBranchDto requestBranchDto) throws SQLException {
        branchService.createBranch(requestBranchDto);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Branch Creation Successfully!", null
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/admin/update/{id}")
    public ResponseEntity<StandardResponseDto> updateBranch(
            @PathVariable("id") String branchId,
            @RequestBody RequestBranchDto data) throws SQLException {
        branchService.updateBranch(data, branchId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Branch updated successfully", null
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/host/delete/{id}")
    public ResponseEntity<StandardResponseDto> deleteBranch(@PathVariable("id") String branchId) {
        branchService.deleteBranch(branchId);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204, "Branch Deleted Successfully!", null
                ),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/visitor/find-by-id/{id}")
    public ResponseEntity<StandardResponseDto> findBranchById(@PathVariable("id") String branchId) throws SQLException {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Branch Found!", branchService.findByBranchId(branchId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-all")
    public ResponseEntity<StandardResponseDto> findAllBranches(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Branches!", branchService.findBranches(page,size,searchText)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/visitor/find-by-hotel-id")
    public ResponseEntity<StandardResponseDto> findAllBranchesByHotelId(
            @RequestParam String hotelId,
            @RequestParam int page,
            @RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "List of the Branches!", branchService.findBranchesByHotelId(page,size,hotelId)
                ),
                HttpStatus.OK
        );
    }
}
