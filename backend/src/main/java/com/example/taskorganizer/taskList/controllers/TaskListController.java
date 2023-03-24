package com.example.taskorganizer.taskList.controllers;

import com.example.taskorganizer.taskList.models.TaskList;
import com.example.taskorganizer.taskList.requests.TaskListsPostRequest;
import com.example.taskorganizer.taskList.requests.TaskListsPutRequest;
import com.example.taskorganizer.taskList.services.interfaces.ITaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task-lists")
public class TaskListController {
    @Autowired
    private ITaskListService service;
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody TaskListsPostRequest requestBody) {
        TaskList taskList = service.create(
                requestBody.getId(),
                requestBody.getTitle(),
                requestBody.getPosition());

        return ResponseEntity.ok(taskList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody TaskListsPutRequest requestBody) {
        TaskList taskList = service.update(
                id,
                requestBody.getTitle(),
                requestBody.getPosition());

        return ResponseEntity.ok(taskList);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }


}
