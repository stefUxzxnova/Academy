package com.staffmanagement.system.services;

import com.staffmanagement.system.entities.Employee;
import com.staffmanagement.system.utils.interfaces.Reader;
import com.staffmanagement.system.utils.interfaces.Writer;

import java.util.List;

public class StaffServiceJson implements Service{

    private final Writer fileWriter;
    private final Reader fileReader;

    public StaffServiceJson(Writer fileWriter, Reader fileReader) {
        this.fileWriter = fileWriter;
        this.fileReader = fileReader;
    }

    @Override
    public List<Employee> getAll() {
        return fileReader.readFile(Employee.class);
    }

    @Override
    public Employee getById(long id) {
        List<Employee> list = fileReader.readFile(Employee.class);
        return list.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean create(Employee employee) {
        List<Employee> list = fileReader.readFile(Employee.class);
        list.add(employee);
        return fileWriter.writeFile(list);
    }

    @Override
    public boolean delete(long id) {
        List<Employee> list = fileReader.readFile(Employee.class);
        boolean removed = list.removeIf(item -> item.getId() == id);
        if (removed) {
            return fileWriter.writeFile(list);
        }
        return false;
    }

    @Override
    public boolean update(Employee modifiedEmployee) {
        List<Employee> list = fileReader.readFile(Employee.class);
        for (Employee e : list) {
            if (e.getId() == modifiedEmployee.getId()) {
                int index = list.indexOf(e);
                list.set(index, modifiedEmployee);
            }
        }
        return fileWriter.writeFile(list);
    }
}
