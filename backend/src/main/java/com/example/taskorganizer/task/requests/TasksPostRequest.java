package com.example.taskorganizer.task.requests;

public class TasksPostRequest {
    private String title;
    private String description;
    private String position;
    private boolean complete;
    public String getTitle() {
        return title;
    }

    private Long assigneeId;
    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }

    public boolean isComplete() {
        return complete;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }
}
