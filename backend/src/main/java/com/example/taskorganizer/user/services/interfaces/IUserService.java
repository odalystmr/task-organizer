package com.example.taskorganizer.user.services.interfaces;

import com.example.taskorganizer.auth.exceptions.UserNotFoundException;
import com.example.taskorganizer.user.models.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findById(Long id);

    User create(String full_name, String username, String email, String password);

    User update(Long id, String full_name, String username, String email);

    void delete(Long id);

    User getUserByUsername(String participant);

    User getUserByToken(String token) throws UserNotFoundException;
}
