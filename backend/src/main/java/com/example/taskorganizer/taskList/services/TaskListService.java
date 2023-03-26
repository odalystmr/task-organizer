package com.example.taskorganizer.taskList.services;

import com.example.taskorganizer.taskList.models.TaskList;
import com.example.taskorganizer.taskList.repositories.TaskListRepository;
import com.example.taskorganizer.taskList.services.interfaces.ITaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListService implements ITaskListService {
    @Autowired
    private TaskListRepository repository;

    @Override
    public List<TaskList> findAll() {
        return repository.findAll();

    }

    @Override
    public TaskList findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public TaskList create(String title, String position) {
        TaskList taskList = new TaskList(title, position);

        return repository.save(taskList);

    }

    @Override
    public TaskList update(Long id, String title, String position) {
        TaskList taskList = findById(id);

        taskList.setTitle(title);
        taskList.setPosition(position);

        return repository.save(taskList);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Long idProject) {

    }
}
