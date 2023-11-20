package com.staffmanagement.system.services;

import com.staffmanagement.system.entities.Employee;

import java.util.List;
public interface Service {
    List<Employee> getAll();
    Employee getById(long id);
    boolean create(Employee employee);
    boolean delete(long id);
    boolean update(Employee employee);
}

