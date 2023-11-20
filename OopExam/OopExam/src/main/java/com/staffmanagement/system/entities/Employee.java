package com.staffmanagement.system.entities;

import java.time.LocalDate;

public class Employee{
    private static long uid = 1;
    private long id;
    private String name;
    private String department;
    private String role;
    private double salary;
    private LocalDate startDate;

    static {

    }

    public Employee(String name, String department, String role, double salary, LocalDate startDate) {
        this.id = uid++;
        this.name = name;
        this.department = department;
        this.role = role;
        this.salary = salary;
        this.startDate = startDate;
    }
    public Employee(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public static void setUid(long biggestId){
        uid = biggestId + 1;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", startDate=" + startDate +
                '}';
    }
}
