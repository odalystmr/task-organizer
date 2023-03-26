package com.example.taskorganizer.task.models;

import com.example.taskorganizer.comment.models.Comment;
import com.example.taskorganizer.project.models.Project;
import com.example.taskorganizer.taskList.models.TaskList;
import com.example.taskorganizer.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "position")
    private String position;
    @Column(name = "complete")
    private boolean complete;

    @ManyToOne
    @JoinColumn(name = "task_list_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"project", "tasks"})
    private TaskList taskList;

    @ManyToOne
    @JoinColumn(name = "assignee_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"projects", "tasks", "comments"})
    private User assignee;


    @OneToMany(mappedBy = "task")
    @JsonIgnoreProperties({"owner", "task"})
    private List<Comment> comments;


    public Task() {
    }

    public Task(String title, String description, String position, boolean complete, User assignee) {
        this.title = title;
        this.description = description;
        this.position = position;
        this.complete = complete;
        this.assignee = assignee;
    }

    public Task( String title, String description, String position, boolean complete) {
        this.title = title;
        this.description = description;
        this.position = position;
        this.complete = complete;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
