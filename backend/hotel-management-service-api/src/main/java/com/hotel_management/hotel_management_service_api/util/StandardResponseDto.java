package com.hotel_management.hotel_management_service_api.util;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardResponseDto {

    private int code;
    private String message;
    private Object data;
}
