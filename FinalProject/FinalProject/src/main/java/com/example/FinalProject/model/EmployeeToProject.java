package com.example.FinalProject.model;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

@Entity
@Table(name = "employeesToProjects")
public class EmployeeToProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public EmployeeToProject() {
    }

    public EmployeeToProject(Employee employee, Project project, LocalDate dateFrom, LocalDate dateTo) {
        this.employee = employee;
        this.project = project;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeId) {
        this.employee = employeeId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project projectId) {
        this.project = projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }


}
