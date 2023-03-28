package com.example.taskorganizer.user.repositories;

import com.example.taskorganizer.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByToken(String token);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
