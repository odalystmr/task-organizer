package com.example.taskorganizer.auth.exceptions;

public class EmailAlreadyExistsException extends Exception{
    @Override
    public String getMessage() {
        return "El email ya esta en uso.";
    }
}