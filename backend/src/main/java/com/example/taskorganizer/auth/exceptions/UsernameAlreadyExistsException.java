package com.example.taskorganizer.auth.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    @Override
    public String getMessage() {
        return "El nombre de usuario ya esta en uso.";
    }
}