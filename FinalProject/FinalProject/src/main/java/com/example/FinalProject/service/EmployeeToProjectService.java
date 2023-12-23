package com.example.FinalProject.service;

import com.example.FinalProject.model.EmployeeToProject;
import com.example.FinalProject.repository.EmployeeToProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeToProjectService {
    private final EmployeeToProjectRepository employeeToProjectRepository;

    public EmployeeToProjectService(EmployeeToProjectRepository employeeToProjectRepository) {
        this.employeeToProjectRepository = employeeToProjectRepository;
    }

    public void save(EmployeeToProject employeeToProject){
        employeeToProjectRepository.save(employeeToProject);
    }
    public boolean existsByEmployeeIdAndProjectIdAndDateFrom(Long employeeId, Long projectId, LocalDate dateFrom){
        return employeeToProjectRepository.existsByEmployeeIdAndProjectIdAndDateFrom(employeeId, projectId, dateFrom);
    };
}
