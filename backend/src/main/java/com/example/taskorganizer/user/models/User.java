package com.example.taskorganizer.user.models;

import com.example.taskorganizer.comment.models.Comment;
import com.example.taskorganizer.project.models.Project;
import com.example.taskorganizer.task.models.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String token;

    @ManyToMany(mappedBy = "participants")
    @JsonIgnoreProperties({"owner", "participants", "taskLists"})
    private List<Project> projects;

    @OneToMany(mappedBy = "assignee")
    @JsonIgnoreProperties({"assignee", "taskList", "comments"})
    private List<Task> tasks;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties({"owner", "task"})
    private List<Comment> comments;


    public User() {
    }

    public User(String fullName, String username, String email, String password, String token) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ",\"fullName\":\"" + fullName + '\"' +
                ", \"username\":\"" + username + '\"' +
                ", \"email\":\"" + email + '\"' +
                '}';
    }
}
