package com.hotel_management.hotel_management_service_api.api;

import com.hotel_management.hotel_management_service_api.dto.request.RequestBranchDto;
import com.hotel_management.hotel_management_service_api.service.BranchService;
import com.hotel_management.hotel_management_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
