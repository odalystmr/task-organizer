package com.example.taskorganizer.project.services;

import com.example.taskorganizer.project.models.Project;
import com.example.taskorganizer.project.repositories.ProjectRepository;
import com.example.taskorganizer.project.services.interfaces.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository repository;

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Project create(Long id, String title, String description) {
        Project project = new Project(id, title, description);

        return repository.save(project);
    }

    @Override
    public Project update(Long id, String title, String description) {
        Project project = findById(id);

        project.setTitle(title);
        project.setDescription(description);

        return repository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Long idOwner) {
//    projectRepository.deleteAll();
    }
}
