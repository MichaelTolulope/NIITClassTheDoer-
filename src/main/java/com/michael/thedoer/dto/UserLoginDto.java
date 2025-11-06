package com.michael.thedoer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserLoginDto {

    @JsonProperty(required = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "email is required")
    private String email;

    @JsonProperty(required = true)
    @Size(min = 6, max = 20, message = "Password must be between 6 - 20 characters")
    private String password;

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
