package com.example.taskorganizer.task.controllers;

import com.example.taskorganizer.task.models.Task;
import com.example.taskorganizer.task.requests.TasksPostRequest;
import com.example.taskorganizer.task.requests.TasksPutRequest;
import com.example.taskorganizer.task.services.interfaces.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskService service;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody TasksPostRequest requestBody) {
        Task task = service.create(
                requestBody.getTitle(),
                requestBody.getDescription(),
                requestBody.getPosition(),
                requestBody.isComplete());

        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody TasksPutRequest requestBody) {
        Task task = service.update(
                id,
                requestBody.getTitle(),
                requestBody.getDescription(),
                requestBody.getPosition(),
                requestBody.isComplete());

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}
