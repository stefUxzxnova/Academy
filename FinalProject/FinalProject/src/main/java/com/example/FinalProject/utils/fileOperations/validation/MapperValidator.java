package com.example.FinalProject.utils.fileOperations.validation;

import com.example.FinalProject.model.Employee;
import com.example.FinalProject.model.Project;
import com.example.FinalProject.service.EmployeeService;
import com.example.FinalProject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The MapperValidator class is responsible for validating Employee and Project entities by their respective IDs.
 */
@Component
public class MapperValidator {
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public MapperValidator(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    /**
     * Validates and retrieves an Employee entity by its ID.
     *
     * @return The Employee entity if found, or null if not found.
     */
    public Employee validateEmployeeById(Long employeeId){
        return employeeService.findById(employeeId);
    }

    /**
     * Validates and retrieves a Project entity by its ID.
     *
     * @return The Project entity if found, or null if not found.
     */
    public Project validateProjectById(Long projectId){
        return projectService.findById(projectId);
    }
}
