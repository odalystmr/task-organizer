package com.example.taskorganizer.comment.requests;

import java.util.Date;

public class CommentsPutRequest {
    private Long id;
    private String comment;
    private Date date;

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
}
