package com.hotel_management.hotel_management_service_auth_service.advisor;

import com.hotel_management.hotel_management_service_auth_service.exception.BadRequestException;
import com.hotel_management.hotel_management_service_auth_service.exception.DuplicateEntryException;
import com.hotel_management.hotel_management_service_auth_service.exception.EntryNotFoundException;
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
                new StandardResponseDto(400, exception.getMessage(), exception),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<StandardResponseDto> handleDuplicateException(DuplicateEntryException exception){
        return new ResponseEntity<>(
                new StandardResponseDto(409, exception.getMessage(), exception),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponseDto> handleEntryNotFoundException(EntryNotFoundException exception){
        return new ResponseEntity<>(
                new StandardResponseDto(404, exception.getMessage(), exception),
                HttpStatus.NOT_FOUND
        );
    }
}
