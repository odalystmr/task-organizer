package com.example.taskorganizer.project.controllers;

import com.example.taskorganizer.project.models.Project;
import com.example.taskorganizer.project.requests.AddProjectParticipantsPostRequest;
import com.example.taskorganizer.project.requests.ProjectsPostRequest;
import com.example.taskorganizer.project.requests.ProjectsPutRequest;
import com.example.taskorganizer.project.services.interfaces.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private IProjectService service;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody ProjectsPostRequest requestBody) {
        Project project = service.create(
                requestBody.getTitle(),
                requestBody.getDescription());

        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProjectsPutRequest requestBody) {
        Project project = service.update(
                id,
                requestBody.getTitle(),
                requestBody.getDescription());

        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PostMapping("/{id}/participants")
    public void addParticipants(@PathVariable("id") Long id, @RequestBody AddProjectParticipantsPostRequest requestBody){
        service.addParticipants(
                id,
                requestBody.getParticipantIds());
    }
}
