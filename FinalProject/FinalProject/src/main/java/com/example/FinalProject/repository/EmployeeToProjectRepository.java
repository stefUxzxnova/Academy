package com.example.FinalProject.repository;

import com.example.FinalProject.model.EmployeeToProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EmployeeToProjectRepository extends JpaRepository<EmployeeToProject, Long> {
    boolean existsByEmployeeIdAndProjectIdAndDateFrom(Long employeeId, Long projectId, LocalDate dateFrom);
}
