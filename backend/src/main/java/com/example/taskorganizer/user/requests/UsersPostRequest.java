package com.example.taskorganizer.user.requests;

public class UsersPostRequest {

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

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
