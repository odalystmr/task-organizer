package com.example.taskorganizer.comment.services.interfaces;

import com.example.taskorganizer.comment.models.Comment;

import java.util.Date;
import java.util.List;

public interface ICommentService {

    List<Comment> findAll();

    Comment findById(Long id);

    Comment create(String comment, Date date);

    Comment update(Long id, String comment, Date date);

    void deleteById(Long id);

    void deleteByTaskId(Long id);
}
