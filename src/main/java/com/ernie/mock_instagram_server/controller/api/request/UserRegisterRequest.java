package com.ernie.mock_instagram_server.controller.api.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class UserRegisterRequest {

    @Length(min = 5, max = 50)
    @NotEmpty
    private String username;

    @Length(min = 5, max = 50)
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
