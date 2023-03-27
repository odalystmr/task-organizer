package com.example.taskorganizer.auth.services;

import com.example.taskorganizer.auth.exceptions.UserNotFoundException;
import com.example.taskorganizer.auth.services.interfaces.IAuthService;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String assignToken(String username) {
        User user = userRepository.findByUsername(username);

        if (user.getToken() != null) {
            return user.getToken();
        }

        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepository.save(user);

        return token;
    }

    @Override
    public void logout(String username) {

    }

    public User getUserByToken(String token) throws UserNotFoundException {
        User user = userRepository.findByToken(token);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }
}
