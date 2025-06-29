package com.example.taskorganizer.comment.requests;

import java.util.Date;

public class CommentsPostRequest {
    private String comment;
    private Date date;


    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
}
