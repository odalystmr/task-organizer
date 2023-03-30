package com.example.taskorganizer.task.services;

import com.example.taskorganizer.comment.services.interfaces.ICommentService;
import com.example.taskorganizer.task.models.Task;
import com.example.taskorganizer.task.repositories.TaskRepository;
import com.example.taskorganizer.task.services.interfaces.ITaskService;
import com.example.taskorganizer.taskList.models.TaskList;
import com.example.taskorganizer.taskList.repositories.TaskListRepository;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private TaskListRepository taskListRepository;

    public List<Task> findAllByTaskListId(Long taskListId) {
        return repository.findAllByTaskListId(taskListId);
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Task create(String title, String description, String position, boolean complete, Long assigneeId, Long taskListId) {
        User assignee = userRepository.findById(assigneeId).orElseThrow();
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow();
        Task task = new Task(title, description, position, complete, assignee);
        task.setTaskList(taskList);


        return repository.save(task);
    }

//    @Override
//    public Task update(Long id, String title, String description, String position, boolean complete, Long assigneeId) {
//        User assignee = userRepository.findById(assigneeId).orElseThrow();
//        Task task = findById(id);
//
//        task.setTitle(title);
//        task.setDescription(description);
//        task.setPosition(position);
//        task.setComplete(complete);
//        task.setAssignee(assignee);
//
//        return repository.save(task);
//    }
    @Override
    public Task markAsComplete(Long id, boolean complete) {
        Task task = findById(id);
        task.setComplete(complete);

        return repository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        commentService.deleteByTaskId(id);
        repository.deleteById(id);
    }

    @Override
    public void deleteByTaskListId(Long id) {
        List<Task> tasks = findAllByTaskListId(id);
        for (Task task : tasks) {
            deleteById(task.getId());
        }
    }
}
