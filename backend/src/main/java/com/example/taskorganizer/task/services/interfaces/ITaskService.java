package com.example.taskorganizer.task.services.interfaces;

import com.example.taskorganizer.task.models.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    public List<Task> findAll();

    public Optional<Task> findById(Long id);

    public Task create(Task task);

    public Task update(Task task);

    public void deleteById(Long id);

    public void deleteAll(Long idTaskList);


}
