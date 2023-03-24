package com.example.taskorganizer.task.requests;

public class TasksPostRequest {
    private Long id;
    private String title;
    private String description;
    private String position;
    private boolean complete;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }

    public boolean isComplete() {
        return complete;
    }
}
