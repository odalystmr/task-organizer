package com.example.taskorganizer.taskList.services.interfaces;

import com.example.taskorganizer.taskList.models.TaskList;

import java.util.List;
import java.util.Optional;

public interface ITaskListService {
    public List<TaskList> findAll();

    public TaskList findById(Long id);

    public TaskList create(String title, String position);

    public TaskList update(Long id, String title, String position);

    public void deleteById(Long id);

    public void deleteAll(Long idProject);

}
