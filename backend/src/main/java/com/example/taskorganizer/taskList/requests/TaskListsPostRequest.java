package com.example.taskorganizer.taskList.requests;

public class TaskListsPostRequest {
    private Long id;
    private String title;
    private String position;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosition() {
        return position;
    }
}
