package com.jsonServer.api.test.bearerToken.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {

    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
