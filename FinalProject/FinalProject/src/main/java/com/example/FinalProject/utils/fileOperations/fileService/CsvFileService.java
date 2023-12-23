package com.example.FinalProject.utils.fileOperations.fileService;

import com.example.FinalProject.model.EmployeesPair;
import com.example.FinalProject.service.EmployeeToProjectService;
import com.example.FinalProject.utils.MainLogic;
import com.example.FinalProject.utils.fileOperations.fileRepository.CsvFileRepository;
import com.example.FinalProject.utils.fileOperations.fileModel.EmployeeToProjectCsv;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * CsvFileService handles operations related to reading CSV files containing
 * EmployeeToProjectCsv data, processing the data, and finding pairs of employees
 * who have worked together the most.
 */
@Service
public class CsvFileService {

    private final EmployeeToProjectService employeeToProjectService;
    private final CsvFileRepository repository;

    public CsvFileService(EmployeeToProjectService employeeToProjectService, CsvFileRepository repository, Mapper mapper) {
        this.employeeToProjectService = employeeToProjectService;
        this.repository = repository;
    }


    /**
     * Reads EmployeeToProjectCsv data from a CSV file.
     *
     * @return A list of EmployeeToProjectCsv objects read from the file.
     */
    public List<EmployeeToProjectCsv> readFile(String fileName){
        try {
            return repository.readFile(fileName);
        }catch (Exception e){
            System.err.println("Error while reading the file");
            return new ArrayList<>();
        }
    }

    /**
     * Finds pairs of employees who have worked together the most based on the data in a CSV file.
     *
     * @return An Optional containing the pair of employees who have worked together the most or null.
     */
    public Optional<EmployeesPair> getEmployeesWorkedMostTogether(String fileName) {
        List<EmployeeToProjectCsv> list = repository.readFile(fileName);
        Map<Long, List<EmployeeToProjectCsv>> projectsMap = MainLogic.convertListToMap(list);
        List<EmployeesPair> employeesPairList = MainLogic.putEmployeesInPairs(projectsMap);

        return findPairWithMaxWorkingDays(employeesPairList);
    }

    /**
     * Finds the pair of employees who have worked together the most based on the working days.
     *
     * @return An Optional containing the pair of employees who have worked together the most,
     *         or an empty Optional if the list is empty.
     */
    private Optional<EmployeesPair> findPairWithMaxWorkingDays(List<EmployeesPair> employeesPairList) {
        return employeesPairList.stream()
                .max(Comparator.comparingInt(EmployeesPair::getWorkingDays));
    }


}
