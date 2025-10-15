package com.hotel_management.hotel_management_service_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("hotel-management-service-api",
                        route -> route.path("/hotel-service/**").uri("http://localhost:9091"))

                .route("hotel-management-service-auth-service",
                        route -> route.path("/user-service/**").uri("http://localhost:9092"))
                .build();
    }
}
