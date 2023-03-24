package com.example.taskorganizer.project.requests;

public class ProjectsPutRequest {
    private Long id;
    private String title;
    private String description;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
