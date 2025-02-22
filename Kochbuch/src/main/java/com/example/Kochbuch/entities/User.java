package com.example.Kochbuch.entities;


import jakarta.validation.constraints.NotBlank;


public class User {

    private long id;

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String username;

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }
}
