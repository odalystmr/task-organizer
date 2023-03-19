package com.example.taskorganizer.task.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
