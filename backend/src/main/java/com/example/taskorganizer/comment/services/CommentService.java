package com.example.taskorganizer.comment.services;

import com.example.taskorganizer.comment.models.Comment;
import com.example.taskorganizer.comment.repositories.CommentRepository;
import com.example.taskorganizer.comment.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentRepository repository;

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Comment create(String text, Date date) {
        Comment comment = new Comment(text, date);
        return repository.save(comment);
    }

    @Override
    public Comment update(Long id, String text, Date date) {
        Comment comment = findById(id);

        comment.setText(text);
        comment.setDate(date);

        return repository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Long idTask) {

    }
}
