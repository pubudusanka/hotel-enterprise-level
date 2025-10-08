package com.hotel_management.hotel_management_service_auth_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemUserRequest {

    @NotBlank(message = "First name name is Required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name name is Required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @NotBlank(message = "Email is Required")
    @Email(message = "Not a Email type, Please enter valid email")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 6, message = "Password must need at least 6 characters")
    private String password;

    private String contact;
}
