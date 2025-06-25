package com.humanbooster.exam.dto;

import com.humanbooster.exam.entity.Project;
import com.humanbooster.exam.entity.TaskStatus;
import com.humanbooster.exam.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    @NotBlank
    private String title;

    @NotBlank
    private TaskStatus status;

    @NotBlank
    private Project project;

    @NotBlank
    private User assignee;
}
