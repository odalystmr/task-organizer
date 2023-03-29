package com.example.taskorganizer.comment.repositories;

import com.example.taskorganizer.comment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByTaskId(Long taskId);
}
