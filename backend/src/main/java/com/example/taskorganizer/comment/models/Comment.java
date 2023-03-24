package com.example.taskorganizer.comment.models;

import com.example.taskorganizer.project.models.Project;
import com.example.taskorganizer.task.models.Task;
import com.example.taskorganizer.taskList.models.TaskList;
import com.example.taskorganizer.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"projects", "tasks", "comments"})
    private User owner;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"assignee", "taskList", "comments"})
    private Task task;



    public Comment() {
    }

    public Comment(Long id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }


}
