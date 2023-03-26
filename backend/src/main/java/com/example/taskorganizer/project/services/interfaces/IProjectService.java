package com.example.taskorganizer.project.services.interfaces;

import com.example.taskorganizer.project.models.Project;

import java.util.List;

public interface IProjectService {
    public List<Project> findAll();

    public Project findById(Long id);

    public Project create(String title, String description);

    public Project update(Long id, String title, String description);

    public void deleteById(Long id);

    public void deleteAll(Long idOwner);
}
