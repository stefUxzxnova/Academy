package com.example.FinalProject.model;

import com.example.FinalProject.utils.fileOperations.fileModel.EmployeeToProjectCsv;

import java.util.Map;

public class EmployeesPair {
    private EmployeeToProjectCsv employeeOne;
    private EmployeeToProjectCsv employeeTwo;

    private Map<Long, Integer> projectsAndDurations;

    public EmployeesPair(EmployeeToProjectCsv employeeOne, EmployeeToProjectCsv employeeTwo, Map<Long, Integer> projectsAndDurations) {
        this.employeeOne = employeeOne;
        this.employeeTwo = employeeTwo;
        this.projectsAndDurations = projectsAndDurations;
    }

    public EmployeesPair() {
    }

    public EmployeeToProjectCsv getEmployeeOne() {
        return employeeOne;
    }

    public void setEmployeeOne(EmployeeToProjectCsv employeeOne) {
        this.employeeOne = employeeOne;
    }

    public EmployeeToProjectCsv getEmployeeTwo() {
        return employeeTwo;
    }

    public void setEmployeeTwo(EmployeeToProjectCsv employeeTwo) {
        this.employeeTwo = employeeTwo;
    }

    public Map<Long, Integer> getProjectsAndDurations() {
        return projectsAndDurations;
    }

    public int getWorkingDays(){
        return projectsAndDurations.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
