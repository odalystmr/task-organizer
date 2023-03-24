package com.example.taskorganizer.comment.services.interfaces;

import com.example.taskorganizer.comment.models.Comment;

import java.util.Date;
import java.util.List;
import java.util.Optional;
public interface ICommentService {

    public List<Comment> findAll();

    public Comment findById(Long id);

    public Comment create(Long id, String comment, Date date);

    public Comment update(Long id, String comment, Date date);

    public void deleteById(Long id);

    public void deleteAll(Long idTask);
}
