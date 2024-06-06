package com.spring.tacospring.dto;

import com.spring.tacospring.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link User} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserRegistrationDTO {
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username must be alphanumeric with no spaces")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank(message = "Delivery name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Delivery name must be a valid name")
    private String fullname;

    @NotBlank(message = "Street is required")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Street must be a valid name")
    private String street;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "City must be a valid name")
    private String city;

    @NotBlank(message = "State is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "State must be the two-letter abbreviation")
    private String state;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^[0-9]{5}$", message = "Zip code must be exactly 5 digits")
    private String zip;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email must be a valid email address")
    private String email;
}
