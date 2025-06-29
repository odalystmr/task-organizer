package com.example.taskorganizer.project.services;

import com.example.taskorganizer.auth.services.interfaces.IAuthService;
import com.example.taskorganizer.project.models.Project;
import com.example.taskorganizer.project.repositories.ProjectRepository;
import com.example.taskorganizer.project.services.interfaces.IProjectService;
import com.example.taskorganizer.taskList.services.interfaces.ITaskListService;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.repositories.UserRepository;
import com.example.taskorganizer.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ITaskListService taskListService;
    @Autowired
    private IAuthService authService;

    @Override
    public List<Project> findAll() {
        User participant = authService.getUserLoggedIn();

        return repository.findProjectsByParticipant(participant.getId());
    }

    @Override
    public Project findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Project create(String title, String description) {
        User owner = authService.getUserLoggedIn();

        Project project = new Project(title, description, owner);

        List<User> participants=  new ArrayList<>();
        participants.add(owner);
        project.setParticipants(participants);


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
        taskListService.deleteByProjectId(id);
        repository.deleteAllParticipantsByProjectId(id);
        repository.deleteById(id);
    }

    public void deleteByUserId(Long id) {
        repository.deleteParticipantsByUserId(id);
        List<Project> projects = repository.findProjectsByOwnerId(id);
        projects.forEach(project -> deleteById(project.getId()));
    }

    public void addParticipants(Long projectId, List<String> newParticipantUsernames) {
        Project project = findById(projectId);
        List<User> projectParticipants = project.getParticipants();

        for (String username : newParticipantUsernames) {
            User user = userRepository.findByUsername(username);
            projectParticipants.add(user);
        }

        project.setParticipants(projectParticipants);
        repository.save(project);
    }
}
