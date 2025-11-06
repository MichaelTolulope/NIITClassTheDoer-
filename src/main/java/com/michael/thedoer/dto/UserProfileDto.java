package com.michael.thedoer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.Period;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileDto {

    @JsonProperty("first-name")
    private String firstName;
    @JsonIgnore
    private String lastName;
    private String email;

    @JsonProperty("full-name")
    private String fullName;

    private int age;

    public UserProfileDto(String firstName, String lastName, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fullName = firstName + " " + lastName;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
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

    public String getFullName() {
        return fullName;
    }

    public int getAge(){
        return age;
    }

}
