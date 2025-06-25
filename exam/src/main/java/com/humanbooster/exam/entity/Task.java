package com.humanbooster.exam.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column
    @NotNull(message = "Le titre est obligatoire")
    private String title;

    @Column
    @NotNull(message = "Le status est obligatoire")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "id_project", nullable = false)
    @JsonManagedReference("project_task")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonManagedReference("task_user")
    private User assignee;
}
