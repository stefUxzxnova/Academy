package com.staffmanagement.system.services;

import com.staffmanagement.system.entities.Employee;
import com.staffmanagement.system.utils.csv.CsvReader;
import com.staffmanagement.system.utils.csv.CsvWriter;
import com.staffmanagement.system.utils.interfaces.Reader;
import com.staffmanagement.system.utils.interfaces.Writer;

import java.util.List;

public class StaffServiceCsv implements Service{
    private final Writer fileWriter;
    private final Reader fileReader;

    public StaffServiceCsv(Writer fileWriter, Reader fileReader) {
        this.fileWriter = fileWriter;
        this.fileReader = fileReader;
    }

    @Override
    public List<Employee> getAll() {
        return fileReader.readFile();
    }

    @Override
    public Employee getById(long id) {
        List<Employee> list = fileReader.readFile(Employee.class);
        return list.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean create(Employee employee) {
        List<Employee> list = fileReader.readFile();
        list.add(employee);
        return fileWriter.writeFile(list);
    }

    @Override
    public boolean delete(long id){
        // TODO: 11.11.2023 г.  
        return true;
    }

    @Override
    public boolean update(Employee employee) {
        // TODO: 11.11.2023 г.  
        return true;
    }
}
