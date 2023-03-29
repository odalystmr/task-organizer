package com.example.taskorganizer.task.controllers;

import com.example.taskorganizer.task.models.Task;
import com.example.taskorganizer.task.requests.TasksPostRequest;
import com.example.taskorganizer.task.requests.TasksPutRequest;
import com.example.taskorganizer.task.services.interfaces.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectId}/task-lists/{taskListId}/tasks")
public class TaskController {
    @Autowired
    private ITaskService service;

    @GetMapping("")
    public ResponseEntity<?> findAll(@PathVariable("taskListId") Long taskListId) {
        return ResponseEntity.ok(service.findAllByTaskListId(taskListId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@PathVariable("taskListId") Long taskListId,@RequestBody TasksPostRequest requestBody) {

        Task task = service.create(
                requestBody.getTitle(),
                requestBody.getDescription(),
                requestBody.getPosition(),
                requestBody.isComplete(),
                requestBody.getAssigneeId(),
                taskListId);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody TasksPutRequest requestBody) {
        Task task = service.update(
                id,
                requestBody.getTitle(),
                requestBody.getDescription(),
                requestBody.getPosition(),
                requestBody.isComplete(),
                requestBody.getAssigneeId());

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}
