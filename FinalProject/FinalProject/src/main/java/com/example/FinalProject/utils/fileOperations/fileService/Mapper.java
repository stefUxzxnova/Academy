package com.example.FinalProject.utils.fileOperations.fileService;

import com.example.FinalProject.model.Employee;
import com.example.FinalProject.model.EmployeeToProject;
import com.example.FinalProject.model.Project;
import com.example.FinalProject.utils.fileOperations.fileModel.EmployeeToProjectCsv;
import com.example.FinalProject.utils.fileOperations.validation.MapperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Mapper class is responsible for converting data objects from one type to another.
 * The conversion from EmployeeToProjectCsv objects to EmployeeToProject objects serves
 * the purpose of transforming the data from a format suitable for reading from a CSV file
 * (CSV-specific representation) to a format suitable for storage in the database
 * (entity-specific representation).
 */
@Component
public class Mapper {
    private MapperValidator mapperValidator;

    public Mapper(MapperValidator mapperValidator) {
        this.mapperValidator = mapperValidator;
    }

    /**
     * Converts an EmployeeToProjectCsv object to an EmployeeToProject object.
     *
     * @param employeeToProjectCsv The source EmployeeToProjectCsv object.
     * @return The converted EmployeeToProject object.
     */
    public EmployeeToProject convertToEmployeeToProject(EmployeeToProjectCsv employeeToProjectCsv){
        EmployeeToProject employeeToProject = new EmployeeToProject();

        // Validate and set Employee
        Employee employee = mapperValidator.validateEmployeeById(employeeToProjectCsv.getEmployeeId());
        if (employee != null) {
            employeeToProject.setEmployee(employee);
        }

        // Validate and set Project
        Project project = mapperValidator.validateProjectById(employeeToProjectCsv.getProjectId());
        if (project != null) {
            employeeToProject.setProject(project);
        }

        // Set DateFrom and DateTo
        employeeToProject.setDateFrom(employeeToProjectCsv.getDateFrom());
        employeeToProject.setDateTo(employeeToProjectCsv.getDateTo());

        return employeeToProject;
    }
}
