package com.hotel_management.hotel_management_service_auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HotelManagementServiceAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementServiceAuthServiceApplication.class, args);
	}

}
