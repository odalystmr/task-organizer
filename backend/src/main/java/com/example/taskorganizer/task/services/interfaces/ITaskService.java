package com.example.taskorganizer.task.services.interfaces;

import com.example.taskorganizer.task.models.Task;

import java.util.List;

public interface ITaskService {

    List<Task> findAllByTaskListId(Long taskListId);

    Task findById(Long id);

    Task create(String title, String description, String position, boolean complete, Long assignee, Long taskListId);

    Task update(Long id, String title, String description, String position, boolean complete, Long assignee);

    void deleteById(Long id);

    void deleteByTaskListId(Long id);
}
