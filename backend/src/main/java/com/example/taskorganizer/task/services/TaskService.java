package com.example.taskorganizer.task.services;

import com.example.taskorganizer.task.models.Task;
import com.example.taskorganizer.task.repositories.TaskRepository;
import com.example.taskorganizer.task.services.interfaces.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Task create(Long id, String title, String description, String position, boolean complete) {
        Task task = new Task(id, title, description, position, complete);

        return repository.save(task);
    }

    @Override
    public Task update(Long id, String title, String description, String position, boolean complete) {
        Task task = findById(id);

        task.setTitle(title);
        task.setDescription(description);
        task.setPosition(position);
        task.setComplete(complete);

        return repository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Long idTaskList) {

    }
}
