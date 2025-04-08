package org.example.model;

import jakarta.validation.constraints.NotBlank;

public class User {

    @NotBlank(message = "Username cannot be empty")
    private String user;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
