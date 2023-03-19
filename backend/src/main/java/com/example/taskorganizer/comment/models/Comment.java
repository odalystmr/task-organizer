package com.example.taskorganizer.comment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comment {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
