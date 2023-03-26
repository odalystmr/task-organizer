package com.example.taskorganizer.task.services.interfaces;

import com.example.taskorganizer.task.models.Task;

import java.util.List;

public interface ITaskService {

    public List<Task> findAll();

    public Task findById(Long id);

    public Task create(String title, String description, String position, boolean complete, Long assignee);

    public Task update(Long id, String title, String description, String position, boolean complete, Long assignee);

    public void deleteById(Long id);

    public void deleteAll(Long idTaskList);


}
