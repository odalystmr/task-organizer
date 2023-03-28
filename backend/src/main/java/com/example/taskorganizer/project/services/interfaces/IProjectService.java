package com.example.taskorganizer.project.services.interfaces;

import com.example.taskorganizer.project.models.Project;

import java.util.List;

public interface IProjectService {
    List<Project> findAll();

    Project findById(Long id);

    Project create(String title, String description);

    Project update(Long id, String title, String description);

    void deleteById(Long id);

    void deleteAll(Long idOwner);

    void addParticipants(Long id, List<String> newParticipantUsernames);
}
