package com.example.FinalProject.utils.fileOperations.fileReader;

import com.example.FinalProject.model.EmployeeToProject;
import com.example.FinalProject.service.EmployeeToProjectService;
import com.example.FinalProject.utils.DateConverter;
import com.example.FinalProject.utils.fileOperations.fileModel.EmployeeToProjectCsv;
import com.example.FinalProject.utils.fileOperations.fileService.Mapper;
import com.example.FinalProject.utils.fileOperations.validation.FileDataValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVReader class for reading EmployeeToProjectCsv data from a CSV file,
 * processing each line, and creating and saving corresponding objects.
 */
@Component
public class CSVReader implements Reader<EmployeeToProjectCsv> {
    private final static int EMPLOYEE_ID_POSITION = 0;
    private final static int PROJECT_ID_POSITION = 1;
    private final static int DATE_FROM_POSITION = 2;
    private final static int DATE_TO_POSITION = 3;

    private final Mapper mapper;
    private final EmployeeToProjectService employeeToProjectService;

    @Value("${file.path}")
    private String filePath;

    /**
     * Constructor for CSVReader.
     *
     * @param mapper                    The mapper for converting between data objects.
     * @param employeeToProjectService  The service for managing EmployeeToProject entities.
     */
    public CSVReader(Mapper mapper, EmployeeToProjectService employeeToProjectService) {
        this.mapper = mapper;
        this.employeeToProjectService = employeeToProjectService;
    }

    /**
     * Reads EmployeeToProjectCsv data from a CSV file and returns a list of objects.
     *
     * @return A list of EmployeeToProjectCsv objects.
     */
    @Override
    public List<EmployeeToProjectCsv> read(String fileName) {
        List<EmployeeToProjectCsv> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(new File(filePath + fileName)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processLine(line, list);
            }
        } catch (IOException e) {
            System.err.println("Error while reading the file");
        }
        return list;
    }

    /**
     * Processes a line from the CSV file.
     *
     * @param line The CSV line to process.
     * @param list The list to add the processed EmployeeToProjectCsv object to.
     */
    private void processLine(String line, List<EmployeeToProjectCsv> list) {
        if (!line.isBlank()) {
            String[] data = line.trim().split("\\s*,\\s*");
            createNewEmployeeToProjectObject(list, data);
        }
    }

    /**
     * Creates a new EmployeeToProjectCsv object from CSV data and adds it to the list if valid.
     *
     * @param list The list to add the new object to.
     * @param data The CSV data.
     */
    private void createNewEmployeeToProjectObject(List<EmployeeToProjectCsv> list, String[] data) {
        if (FileDataValidator.isEmployeeToProjectObjectDataValid(data)) {
            EmployeeToProjectCsv employeeToProjectCsv = createNewEmployeeToProjectObject(data);
            if (saveToDatabase(employeeToProjectCsv)) {
                list.add(employeeToProjectCsv);
            }
        } else {
            System.err.println("Invalid row");
        }
    }

    /**
     * Creates a new EmployeeToProjectCsv object from CSV data.
     *
     * @param data The CSV data.
     * @return A new EmployeeToProjectCsv object.
     */
    private EmployeeToProjectCsv createNewEmployeeToProjectObject(String[] data) {
        Long userId = Long.parseLong(data[EMPLOYEE_ID_POSITION]);
        Long projectId = Long.parseLong(data[PROJECT_ID_POSITION]);
        LocalDate dateFrom = DateConverter.convertStringToDate(data[DATE_FROM_POSITION]);
        LocalDate dateTo = DateConverter.convertStringToDate(data[DATE_TO_POSITION]);
        return new EmployeeToProjectCsv(userId, projectId, dateFrom, dateTo);
    }

    /**
     * Saves an EmployeeToProjectCsv object to the database if it doesn't already exist.
     *
     * @param item The EmployeeToProjectCsv object to save.
     * @return true if saved successfully, false otherwise.
     */
    public boolean saveToDatabase(EmployeeToProjectCsv item) {
        if (!employeeToProjectService.existsByEmployeeIdAndProjectIdAndDateFrom(item.getEmployeeId(), item.getProjectId(), item.getDateFrom())) {
            try {
                EmployeeToProject employeeToProject = mapper.convertToEmployeeToProject(item);
                employeeToProjectService.save(employeeToProject);
                return true;
            } catch (Exception e) {
                System.err.println("Error occurred while saving the object into the database");
                return false;
            }
        }
        return true;
    }
}
