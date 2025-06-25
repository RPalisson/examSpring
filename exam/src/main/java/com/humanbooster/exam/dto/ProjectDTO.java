package com.humanbooster.exam.dto;

import com.humanbooster.exam.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    @NotBlank
    private String name;

    @NotBlank
    private User creator;
}
