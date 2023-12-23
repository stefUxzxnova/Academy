package com.example.FinalProject.utils.fileOperations.fileRepository;

import com.example.FinalProject.utils.fileOperations.fileModel.EmployeeToProjectCsv;
import com.example.FinalProject.utils.fileOperations.fileReader.CSVReader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CsvFileRepository {
    private List<EmployeeToProjectCsv> list;
    private final CSVReader csvReader;

    public CsvFileRepository(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public List<EmployeeToProjectCsv> readFile(String filePath) {
        try {
            list = csvReader.read(filePath);
            return list;
        } catch (Exception e) {
            System.err.printf(String.format("Error while reading the file %s", filePath));
            return new ArrayList<>();
        }
    }
}
