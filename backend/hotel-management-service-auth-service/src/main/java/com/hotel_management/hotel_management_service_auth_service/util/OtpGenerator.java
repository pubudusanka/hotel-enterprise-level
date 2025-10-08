package com.hotel_management.hotel_management_service_auth_service.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpGenerator {

    public String generateOtp(int length){
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }

        while (stringBuilder.charAt(0)=='0'){
            stringBuilder.setCharAt(0, (char)(random.nextInt(9)+'1'));
        }
        return stringBuilder.toString();

    }
}
