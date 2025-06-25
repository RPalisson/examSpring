package com.humanbooster.exam.controller;

import com.humanbooster.exam.dto.ProjectDTO;
import com.humanbooster.exam.dto.TaskDTO;
import com.humanbooster.exam.entity.Project;
import com.humanbooster.exam.service.ProjectService;
import com.humanbooster.exam.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exam/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        Project project = projectService.toEntity(projectDTO);
        Project savedProject = projectService.save(project);
        ProjectDTO savedDTO = projectService.toDTO(savedProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id){
        return projectService.findById(id)
                .map(project -> ResponseEntity.ok(projectService.toDTO(project)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksByProjectId(@PathVariable Long id){
        Project project = projectService.findById(id).get();
        if (project==null)return ResponseEntity.notFound().build();

        List<TaskDTO> tasks = taskService.findByProject(project)
                .stream().map(taskService::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }
}
