package com.example.taskorganizer.taskList.services.interfaces;

import com.example.taskorganizer.taskList.models.TaskList;

import java.util.List;

public interface ITaskListService {
    List<TaskList> findAllByProjectId(Long projectId);

    TaskList findById(Long id);

    TaskList create(String title, String position, Long projectId);

    TaskList update(Long id, String title, String position);

    void deleteById(Long id);

    void deleteByProjectId(Long id);
}
