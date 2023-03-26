package com.example.taskorganizer.project.models;

import com.example.taskorganizer.taskList.models.TaskList;
import com.example.taskorganizer.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"projects", "tasks", "comments"})
    private User owner;

    @ManyToMany
    @JoinTable(name = "project_users",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties({"projects", "tasks", "comments"})
    private List<User> participants;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties({"project", "tasks"})
    private List<TaskList> taskLists;

    public Project() {
    }

    public Project(String title, String description, User owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<TaskList> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(List<TaskList> taskLists) {
        this.taskLists = taskLists;
    }


}
