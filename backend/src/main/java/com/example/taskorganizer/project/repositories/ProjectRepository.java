package com.example.taskorganizer.project.repositories;

import com.example.taskorganizer.project.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT p.* FROM projects p JOIN project_users pu on p.id = pu.project_id WHERE pu.user_id = :participantId",
            nativeQuery = true)
    List<Project> findProjectsByParticipant(@Param("participantId") Long participantId);
}
