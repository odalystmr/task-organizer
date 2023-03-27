package com.example.taskorganizer.auth.services.interfaces;

import com.example.taskorganizer.auth.exceptions.UserNotFoundException;
import com.example.taskorganizer.user.models.User;

public interface IAuthService {

    public String assignToken(String username);

    void logout(String username);

    public User getUserByToken(String token) throws UserNotFoundException;

}
