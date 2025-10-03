package com.hotel_management.hotel_management_service_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HotelManagementServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementServiceGatewayApplication.class, args);
	}

}
