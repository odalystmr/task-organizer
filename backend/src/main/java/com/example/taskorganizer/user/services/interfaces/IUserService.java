package com.example.taskorganizer.user.services.interfaces;

import com.example.taskorganizer.user.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

	public List<User> findAll();

	public Optional<User> findById(Long id);

	public User create(User user);

	public User update(User user);

	public void delete(Long id);

}
