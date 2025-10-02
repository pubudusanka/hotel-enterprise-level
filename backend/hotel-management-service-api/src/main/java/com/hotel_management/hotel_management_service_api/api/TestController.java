package com.hotel_management.hotel_management_service_api.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel-service/test")
public class TestController {

    @GetMapping("/connection-test")
    public String connectionTest() {
        return "connection test passed!";
    }
}
