package com.hotel_management.hotel_management_service_auth_service.api;

import com.hotel_management.hotel_management_service_auth_service.config.JwtService;
import com.hotel_management.hotel_management_service_auth_service.dto.request.SystemUserRequest;
import com.hotel_management.hotel_management_service_auth_service.service.SystemUserService;
import com.hotel_management.hotel_management_service_auth_service.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/visitors/resend")
    public ResponseEntity<StandardResponseDto> resend(@RequestParam String email, @RequestParam String type){
        systemUserService.resend(email, type);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Please check your Email!", null
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/visitors/forgot-password-request-code")
    public ResponseEntity<StandardResponseDto> forgotPasswordRequest(@RequestParam String email){
        systemUserService.forgotPasswordSendVerificationCode(email);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Please check your Email!", null
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/visitors/verify-reset")
    public ResponseEntity<StandardResponseDto> verifyReset(@RequestParam String email, @RequestParam String otp){
        boolean isVerified = systemUserService.verifyReset(otp, email);

        return new ResponseEntity<>(
                new StandardResponseDto(
                        isVerified?200:400, isVerified?"Verified!":"Try again!", isVerified
                ),
                isVerified?HttpStatus.OK:HttpStatus.BAD_REQUEST
        );
    }
}
