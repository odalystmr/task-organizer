package com.example.taskorganizer.comment.services.interfaces;

import com.example.taskorganizer.comment.models.Comment;

import java.util.List;
import java.util.Optional;
public interface ICommentService {

    public List<Comment> findAll();

    public Optional<Comment> findById(Long id);

    public Comment create(Comment comment);

    public Comment update(Comment comment);

    public void deleteById(Long id);

    public void deleteAll(Long idTask);
}
