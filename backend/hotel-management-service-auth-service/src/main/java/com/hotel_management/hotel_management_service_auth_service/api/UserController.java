package com.hotel_management.hotel_management_service_auth_service.api;

import com.hotel_management.hotel_management_service_auth_service.config.JwtService;
import com.hotel_management.hotel_management_service_auth_service.dto.request.SystemUserRequest;
import com.hotel_management.hotel_management_service_auth_service.service.SystemUserService;
import com.hotel_management.hotel_management_service_auth_service.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user-service/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final SystemUserService systemUserService;
    private final JwtService jwtService;

    @PostMapping("/visitors/signup")
    public ResponseEntity<StandardResponseDto> createUser(@RequestBody SystemUserRequest data){
        systemUserService.createSystemUser(data);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "User Account was Created!", null
                ),
                HttpStatus.CREATED
        );
    }
}
