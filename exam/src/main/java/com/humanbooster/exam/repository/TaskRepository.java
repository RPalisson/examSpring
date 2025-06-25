package com.humanbooster.exam.repository;

import com.humanbooster.exam.entity.Project;
import com.humanbooster.exam.entity.Task;
import com.humanbooster.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignee(User assignee);
    List<Task> findByProject(Project project);
}
