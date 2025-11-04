package com.michael.thedoer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginDto {

    @JsonProperty(required = true)
    private String email;
    @JsonProperty(required = true)
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
