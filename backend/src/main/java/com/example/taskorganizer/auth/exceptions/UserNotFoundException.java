package com.example.taskorganizer.auth.exceptions;

public class UserNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "El usuario no existe";
    }
}