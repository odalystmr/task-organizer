package com.example.taskorganizer.auth.requests;

public class RegisterPostRequest {
    private String fullName;
    private String username;
    private String email;
    private String password;


    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
