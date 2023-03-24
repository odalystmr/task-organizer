package com.example.taskorganizer.user.services.interfaces;

import com.example.taskorganizer.user.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

	public List<User> findAll();

	public User findById(Long id);

	public User create(Long id, String full_name, String username, String email, String password);

	public User update(Long id, String full_name, String username, String email, String password);

	public void delete(Long id);

}
