package com.example.taskorganizer.project.services;

import com.example.taskorganizer.project.models.Project;
import com.example.taskorganizer.project.repositories.ProjectRepository;
import com.example.taskorganizer.project.services.interfaces.IProjectService;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository repository;
    @Autowired
    private IUserService userService;

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Project create(String title, String description) {
        User owner = userService.getUserLoggedIn();

        Project project = new Project(title, description, owner);

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

    public void addParticipants(Long projectId, List<String> newParticipantUsernames) {
        Project project = findById(projectId);
        List<User> projectParticipants = project.getParticipants();

        for (String username : newParticipantUsernames) {
            User user = userService.getUserByUsername(username);
            projectParticipants.add(user);

        }

        project.setParticipants(projectParticipants);

        repository.save(project);
    }
}
