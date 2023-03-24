package com.example.taskorganizer.task.services.interfaces;

import com.example.taskorganizer.task.models.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    public List<Task> findAll();

    public Task findById(Long id);

    public Task create(Long id, String title, String description, String position, boolean complete );

    public Task update(Long id, String title, String description, String position, boolean complete );

    public void deleteById(Long id);

    public void deleteAll(Long idTaskList);


}
