package com.example.taskorganizer.auth.services;

import com.example.taskorganizer.auth.exceptions.EmailAlreadyExistsException;
import com.example.taskorganizer.auth.exceptions.UserNotFoundException;
import com.example.taskorganizer.auth.exceptions.UsernameAlreadyExistsException;
import com.example.taskorganizer.auth.services.interfaces.IAuthService;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String assignToken(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException();
        }

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

    @Override
    public void register(String fullName, String username, String email, String password) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException();
        }

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }

        User user = new User(fullName, username, email, passwordEncoder.encode(password), null);

        userRepository.save(user);
    }

    @Override
    public String login(String username, String password) throws UserNotFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = assignToken(username);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return token;
    }
}
