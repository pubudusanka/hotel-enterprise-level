package com.hotel_management.hotel_management_service_auth_service;

import com.hotel_management.hotel_management_service_auth_service.dto.request.SystemUserRequest;
import com.hotel_management.hotel_management_service_auth_service.service.SystemUserService;
import com.hotel_management.hotel_management_service_auth_service.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class HotelManagementServiceAuthServiceApplication implements CommandLineRunner {

	private final SystemUserService systemUserService;
	private final PasswordGenerator passwordGenerator;

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementServiceAuthServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SystemUserRequest user1 = new SystemUserRequest("ABC","XYZ","22ug1-0251@sltc.ac.lk", passwordGenerator.generatePassword(), "0788884110");
		SystemUserRequest user2 = new SystemUserRequest("STY","WTZ","example@gmail.com", passwordGenerator.generatePassword(), "0777884120");

		systemUserService.initializeHost(Arrays.asList(user1,user2));
	}

}
