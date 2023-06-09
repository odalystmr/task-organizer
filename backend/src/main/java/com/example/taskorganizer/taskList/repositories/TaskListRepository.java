package com.example.taskorganizer.taskList.repositories;

import com.example.taskorganizer.taskList.models.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    List<TaskList> findAllByProjectId(Long projectId);
}
