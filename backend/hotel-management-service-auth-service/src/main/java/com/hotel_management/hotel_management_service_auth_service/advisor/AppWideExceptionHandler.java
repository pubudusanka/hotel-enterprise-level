package com.hotel_management.hotel_management_service_auth_service.advisor;

import com.hotel_management.hotel_management_service_auth_service.exception.BadRequestException;
import com.hotel_management.hotel_management_service_auth_service.util.StandardResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardResponseDto> handleBadRequestException(BadRequestException exception){
        return new ResponseEntity<>(
                new StandardResponseDto(409, exception.getMessage(), exception),
                HttpStatus.BAD_REQUEST
        );
    }
}
