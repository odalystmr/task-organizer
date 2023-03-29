package com.example.taskorganizer.taskList.services;

import com.example.taskorganizer.project.repositories.ProjectRepository;
import com.example.taskorganizer.task.services.interfaces.ITaskService;
import com.example.taskorganizer.taskList.models.TaskList;
import com.example.taskorganizer.taskList.repositories.TaskListRepository;
import com.example.taskorganizer.taskList.services.interfaces.ITaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService implements ITaskListService {
    @Autowired
    private TaskListRepository repository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ITaskService taskService;

    @Override
    public List<TaskList> findAllByProjectId(Long projectId) {
        return repository.findAllByProjectId(projectId);

    }

    @Override
    public TaskList findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public TaskList create(String title, String position, Long projectId) {
        TaskList taskList = new TaskList(title, position);

        taskList.setProject(projectRepository.findById(projectId).orElseThrow());

        return repository.save(taskList);

    }

    @Override
    public TaskList update(Long id, String title, String position) {
        TaskList taskList = findById(id);

        taskList.setTitle(title);
        taskList.setPosition(position);

        return repository.save(taskList);
    }

    @Override
    public void deleteById(Long id) {
        taskService.deleteByTaskListId(id);
        repository.deleteById(id);
    }

    @Override
    public void deleteByProjectId(Long id) {
        List<TaskList> taskLists = findAllByProjectId(id);
        for (TaskList taskList : taskLists) {
            deleteById(taskList.getId());
        }
    }
}
