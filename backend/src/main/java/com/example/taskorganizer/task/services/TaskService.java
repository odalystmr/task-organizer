package com.example.taskorganizer.task.services;

import com.example.taskorganizer.task.models.Task;
import com.example.taskorganizer.task.repositories.TaskRepository;
import com.example.taskorganizer.task.services.interfaces.ITaskService;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository repository;
    @Autowired
    private IUserService userService;

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Task create(String title, String description, String position, boolean complete, Long assigneeId) {
        User assignee = userService.findById(assigneeId);

        Task task = new Task(title, description, position, complete, assignee);

        return repository.save(task);
    }

    @Override
    public Task update(Long id, String title, String description, String position, boolean complete, Long assigneeId) {
        User assignee = userService.findById(assigneeId);
        Task task = findById(id);

        task.setTitle(title);
        task.setDescription(description);
        task.setPosition(position);
        task.setComplete(complete);
        task.setAssignee(assignee);

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
