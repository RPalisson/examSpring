package com.humanbooster.exam.controller;

import com.humanbooster.exam.dto.ProjectDTO;
import com.humanbooster.exam.dto.TaskDTO;
import com.humanbooster.exam.dto.UserDTO;
import com.humanbooster.exam.entity.User;
import com.humanbooster.exam.service.ProjectService;
import com.humanbooster.exam.service.TaskService;
import com.humanbooster.exam.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exam/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.toEntity(userDTO);
        User savedUser = userService.save(user);
        UserDTO savedDTO = userService.toDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(userService.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksByUserId(@PathVariable Long id){
        User user = userService.findById(id).get();
        if (user==null)return ResponseEntity.notFound().build();

        List<TaskDTO> tasks = taskService.findByAssignee(user)
                .stream().map(taskService::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjectDTO>> getProjectByUserId(@PathVariable Long id){
        User user = userService.findById(id).get();
        if (user==null)return ResponseEntity.notFound().build();

        List<ProjectDTO> projects = projectService.findByCreator(user)
                .stream().map(projectService::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(projects);
    }
}
