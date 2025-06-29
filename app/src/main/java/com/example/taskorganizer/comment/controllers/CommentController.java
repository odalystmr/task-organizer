package com.example.taskorganizer.comment.controllers;

import com.example.taskorganizer.comment.models.Comment;
import com.example.taskorganizer.comment.requests.CommentsPostRequest;
import com.example.taskorganizer.comment.requests.CommentsPutRequest;
import com.example.taskorganizer.comment.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private ICommentService service;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody CommentsPostRequest requestBody) {
        Comment comment = service.create(
                requestBody.getComment(),
                requestBody.getDate());

        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CommentsPutRequest requestBody) {
        Comment comment = service.update(
                id,
                requestBody.getComment(),
                requestBody.getDate());

        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}
