package com.humanbooster.exam.repository;

import com.humanbooster.exam.entity.Project;
import com.humanbooster.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCreator(User creator);
}
