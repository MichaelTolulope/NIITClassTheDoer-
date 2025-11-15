package com.michael.thedoer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class CreateUserDto {
    @JsonProperty(required = true)
    private String  firstName;
    @JsonProperty(required = true)
    private String lastName;
    @JsonProperty(required = true)
    @Email
    private String email;
    @JsonProperty(required = true)
    @Past
    private String dateOfBirth;
    @JsonProperty(required = true)
    @Size(min = 8, message = "password must be a minimun of 8 characters")
    private String password;

    public CreateUserDto(String firstName, String lastName, String email, String dateOfBirth, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
