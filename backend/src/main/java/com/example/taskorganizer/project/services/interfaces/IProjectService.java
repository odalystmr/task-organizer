package com.example.taskorganizer.project.services.interfaces;

import com.example.taskorganizer.project.models.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    public List<Project> findAll();

    public Optional<Project> findById(Long id);

    public Project create(Project project);

    public Project update(Project project);

    public void deleteById(Long id);

    public void deleteAll(Long idOwner);
}
