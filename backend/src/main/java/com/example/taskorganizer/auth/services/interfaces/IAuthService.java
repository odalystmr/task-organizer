package com.example.taskorganizer.auth.services.interfaces;

import com.example.taskorganizer.auth.exceptions.EmailAlreadyExistsException;
import com.example.taskorganizer.auth.exceptions.UserNotFoundException;
import com.example.taskorganizer.auth.exceptions.UsernameAlreadyExistsException;
import com.example.taskorganizer.user.models.User;

public interface IAuthService {

    String assignToken(String username) throws UserNotFoundException;

    void logout(String username);

    User getUserByToken(String token) throws UserNotFoundException;

    void register(String fullName, String username, String email, String password) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

    String login(String username, String password) throws UserNotFoundException;
}
