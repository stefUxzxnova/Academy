package com.example.FinalProject.service;

import com.example.FinalProject.model.Project;
import com.example.FinalProject.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectService implements IService<Project>{
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));

    }

    @Override
    public Project save(Project project) {
        //todo validations
        return projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        boolean exists = projectRepository.existsById(id);
        if (exists) {
            projectRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Invalid Id");
        }
    }
}
