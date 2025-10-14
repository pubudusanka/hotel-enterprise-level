package com.hotel_management.hotel_management_service_auth_service.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class EmailTemplateHelper {

    public String loadHtmlTemplate(String templateName) {
        byte[] fileData;
        try {
            ClassPathResource resource = new ClassPathResource(templateName);
            fileData = resource.getInputStream().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new String(fileData, StandardCharsets.UTF_8);
    }
}