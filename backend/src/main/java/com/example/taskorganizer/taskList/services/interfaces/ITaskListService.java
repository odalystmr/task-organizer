package com.example.taskorganizer.taskList.services.interfaces;

import com.example.taskorganizer.taskList.models.TaskList;

import java.util.List;
import java.util.Optional;

public interface ITaskListService {
    public List<TaskList> findAll();

    public Optional<TaskList> findById(Long id);

    public TaskList create(TaskList taskList);

    public TaskList update(TaskList taskList);

    public void deleteById(Long id);

    public void deleteAll(Long idProject);

}
