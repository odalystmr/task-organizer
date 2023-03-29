package com.example.taskorganizer.taskList.models;

import com.example.taskorganizer.comment.models.Comment;
import com.example.taskorganizer.project.models.Project;
import com.example.taskorganizer.task.models.Task;
import com.example.taskorganizer.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "task_lists")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"owner", "participants", "taskLists"})
    private Project project;

    @OneToMany(mappedBy = "taskList")
    @JsonIgnoreProperties({ "taskList", "comments"})
    private List<Task> tasks;


    public TaskList() {
    }

    public TaskList(String title, String position) {
        this.title = title;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
