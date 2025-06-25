package com.humanbooster.exam.controller;

import com.humanbooster.exam.dto.TaskDTO;
import com.humanbooster.exam.entity.Task;
import com.humanbooster.exam.entity.TaskStatus;
import com.humanbooster.exam.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task task = taskService.toEntity(taskDTO);
        Task savedTask = taskService.save(task);
        TaskDTO savedDTO = taskService.toDTO(savedTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task task){
        if (!taskService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Task updatedBooking = taskService.update(id, task);
        return ResponseEntity.ok(updatedBooking);
    }
}
