package com.humanbooster.exam.service;

import com.humanbooster.exam.dto.TaskDTO;
import com.humanbooster.exam.entity.Project;
import com.humanbooster.exam.entity.Task;
import com.humanbooster.exam.entity.User;
import com.humanbooster.exam.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> findAll(){return taskRepository.findAll();}

    @Transactional(readOnly = true)
    public Optional<Task> findById(Long id){return taskRepository.findById(id);}

    @Transactional(readOnly = true)
    public List<Task> findByUser(User user){return taskRepository.findByUser(user);}

    @Transactional(readOnly = true)
    public List<Task> findByProject(Project project){return taskRepository.findByProject(project);}

    public Task save(Task task){return taskRepository.save(task);}

    public Task update(Long id, Task task){
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteById(Long id){taskRepository.deleteById(id);}

    public TaskDTO toDTO(Task task){
        if (task==null) return null;
        return new TaskDTO(
                task.getTitle(),
                task.getStatus(),
                task.getProject(),
                task.getAssignee()
        );
    }

    public Task toEntity(TaskDTO dto){
        if (dto==null) return null;
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setStatus(dto.getStatus());
        task.setProject(dto.getProject());
        task.setAssignee(dto.getAssignee());
        return task;
    }
}
