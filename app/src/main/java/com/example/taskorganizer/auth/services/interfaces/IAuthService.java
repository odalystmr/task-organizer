package com.example.taskorganizer.auth.services.interfaces;

import com.example.taskorganizer.auth.exceptions.EmailAlreadyExistsException;
import com.example.taskorganizer.auth.exceptions.UserNotFoundException;
import com.example.taskorganizer.auth.exceptions.UsernameAlreadyExistsException;
import com.example.taskorganizer.user.models.User;
import org.springframework.security.core.context.SecurityContext;

public interface IAuthService {

    String assignToken(String username) throws UserNotFoundException;

    void logout(String username);

    void register(String fullName, String username, String email, String password) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

    SecurityContext getSecurityContext();

    User getUserLoggedIn();
}
