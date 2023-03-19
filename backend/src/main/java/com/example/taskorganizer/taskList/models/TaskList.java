package com.example.taskorganizer.taskList.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TaskList {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
