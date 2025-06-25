package com.humanbooster.exam.service;

import com.humanbooster.exam.dto.ProjectDTO;
import com.humanbooster.exam.entity.Project;
import com.humanbooster.exam.entity.User;
import com.humanbooster.exam.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<Project> findAll(){ return projectRepository.findAll(); }

    @Transactional(readOnly = true)
    public Optional<Project> findById(Long id){ return projectRepository.findById(id); }

    @Transactional(readOnly = true)
    public List<Project> findByUser(User user){ return projectRepository.findByUser(user); }

    public Project save(Project project){ return projectRepository.save(project); }

    public Project update(Long id, Project project){
        project.setId(id);
        return projectRepository.save(project);
    }

    public void deleteById(Long id){ projectRepository.deleteById(id); }

    public ProjectDTO toDTO(Project project) {
        if (project == null) return null;
        return new ProjectDTO(
                project.getName(),
                project.getCreator()
        );
    }

    public Project toEntity(ProjectDTO dto) {
        if (dto == null) return null;
        Project project = new Project();
        project.setName(dto.getName());
        project.setCreator(dto.getCreator());
        return project;
    }
}
