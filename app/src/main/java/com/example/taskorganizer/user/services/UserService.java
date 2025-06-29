package com.example.taskorganizer.user.services;

import com.example.taskorganizer.auth.exceptions.UserNotFoundException;
import com.example.taskorganizer.project.services.ProjectService;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.repositories.UserRepository;
import com.example.taskorganizer.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProjectService projectService;


    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public User create(String fullName, String username, String email, String password) {
        User user = new User(fullName, username, email, password, null);

        return repository.save(user);
    }

    public User update(Long id, String fullName, String username, String email) {
        User user = findById(id);

        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);

        return repository.save(user);
    }

    public void delete(Long id) {
        projectService.deleteByUserId(id);
        repository.deleteById(id);
    }


    public User getUserByUsername(String username){
        return repository.findByUsername(username);
    }

    public User getUserByToken(String token) throws UserNotFoundException {
        User user = repository.findByToken(token);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }
}
