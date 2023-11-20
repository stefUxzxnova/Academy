package com.staffmanagement.system.managers;

import com.staffmanagement.system.entities.Employee;
import com.staffmanagement.system.entities.enums.Department;
import com.staffmanagement.system.managers.validators.EmployeeValidator;
import com.staffmanagement.system.services.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StaffManager implements Manager{
    private final Service service;

    public StaffManager(Service service) {
        this.service = service;
        getMaxId();
    }

    /**
     * when we start the program, since we already have employees added to the file
     * we take the id of the last one (biggestId)
     */
    private void getMaxId() {
        Employee employeeWithBiggestId = service.getAll().stream().max(Comparator.comparingLong(Employee::getId)).orElse(null);
        if (employeeWithBiggestId != null) {
            Employee.setUid(employeeWithBiggestId.getId());
        }
    }

    @Override
    public void executeCommand(int command, Scanner scanner) {
            switch (command){
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    listAllEmployees();
                    break;
                case 3:
                    deleteEmployee(scanner);
                    break;
                case 4:
                    editEmployee(scanner);
                    break;
                case 5:
                    searchById(scanner);
                    break;
                case 6:
                    searchByName(scanner);
                    break;
                case 7:
                    searchByDepartment(scanner);
                    break;
                case 8:
                    sortByName();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
    }

    private void editEmployee(Scanner scanner) {
        String id;
        do {
            System.out.print("Id: ");
            id = scanner.next();
        } while (!EmployeeValidator.isIdValid(id));
        Employee employee = service.getById(Long.parseLong(id));
        if (employee == null) {
            System.out.println("There is no employee with id " + id);
            return;
        }
        System.out.println("Enter employee details:");
        String name;
        do {
            System.out.print("Name: ");
            name = scanner.next();
        } while (!EmployeeValidator.isNameValid(name));

        System.out.println("Select Department:");
        for (Department department : Department.values()) {
            System.out.println(department.ordinal() + ". " + department.getDepartment());
        }
        int departmentChoice;
        Department department;
        do {
            System.out.print("Choose a department: ");
            departmentChoice = scanner.nextInt();
            department = Department.values()[departmentChoice];
        } while (!EmployeeValidator.isDepartmentValid(department));
        scanner.nextLine(); // Consume the newline character

        String role;
        do {
            System.out.print("Role: ");
            role = scanner.next();
        } while (!EmployeeValidator.isRoleValid(role));

        double salary;
        do {
            System.out.print("Salary: ");
            salary = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
        } while (!EmployeeValidator.isSalaryValid(salary));

        Employee modifiedEmployee = employee;
        modifiedEmployee.setName(name);
        modifiedEmployee.setRole(role);
        modifiedEmployee.setDepartment(department.getDepartment());
        modifiedEmployee.setSalary(salary);
        if (service.update(modifiedEmployee)) {
            System.out.println("Employee updated successfully");
        }else{
            System.out.println("Employee was not updated successfully");
        }
    }

    private void searchByDepartment(Scanner scanner) {
        System.out.println("Select Department:");
        for (Department department : Department.values()) {
            System.out.println(department.ordinal() + ". " + department.getDepartment());
        }
        int departmentChoice;
        Department department;
        do {
            System.out.print("Choose a department: ");
            departmentChoice = scanner.nextInt();
            department = Department.values()[departmentChoice];
        } while (!EmployeeValidator.isDepartmentValid(department));
        Department finalDepartment1 = department;
        List<Employee> list = service.getAll().stream().filter(e -> e.getDepartment().equals(finalDepartment1.getDepartment())).toList();
        if (!list.isEmpty()) {
            list.forEach(System.out::println);
        }else{
            System.out.println("There are no employees in department " + department.getDepartment());
        }
    }

    private void deleteEmployee(Scanner scanner) {
        String id;
        do {
            System.out.print("Enter id: ");
            id = scanner.next();
        } while (!EmployeeValidator.isIdValid(id));
        List<Employee> list = service.getAll();
        String finalId = id;
        Employee employee = list.stream().filter(e -> e.getId() == Long.parseLong(finalId)).findFirst().orElse(null);
        if (employee != null) {
            service.delete(employee.getId());
            System.out.println("Employee deleted successfully!");
        }else{
            System.out.println("There is no employee with id = " + id);
        }
    }

    private void sortByName() {
       service.getAll()
               .stream()
               .sorted(Comparator.comparing(Employee::getName))
               .forEach(System.out::println);
    }

    private void searchById(Scanner scanner) {
        String id;
        do {
            System.out.print("Id: ");
            id = scanner.next();
        } while (!EmployeeValidator.isIdValid(id));
        Employee employee = service.getById(Long.parseLong(id));
        if (employee != null) {
            System.out.println(employee);
        }else{
            System.out.println("There is no employee with id = " + id);
        }
    }

    private void searchByName(Scanner scanner) {
        String name;
        do {
            System.out.print("Name: ");
            name = scanner.next();
        } while (!EmployeeValidator.isNameValid(name));
        List<Employee> list = service.getAll();
        String finalName = name;
        Employee employee = list.stream().filter(e -> e.getName().equalsIgnoreCase(finalName)).findFirst().orElse(null);
        if (employee != null) {
            System.out.println(employee);
        }else{
            System.out.println("There is no employee with name = " + name);
        }
    }

    private void listAllEmployees() {
        service.getAll().forEach(e -> System.out.println(e.toString()));
    }

    private void addEmployee(Scanner scanner) {
        System.out.println("Enter employee details:");
        String name;
        do {
            System.out.print("Name: ");
            name = scanner.next();
        } while (!EmployeeValidator.isNameValid(name));

        System.out.println("Select Department:");
        for (Department department : Department.values()) {
            System.out.println(department.ordinal() + ". " + department.getDepartment());
        }
        int departmentChoice;
        Department department;
        do {
            System.out.print("Choose a department: ");
            departmentChoice = scanner.nextInt();
            department = Department.values()[departmentChoice];
        } while (!EmployeeValidator.isDepartmentValid(department));
        scanner.nextLine(); // Consume the newline character

        String role;
        do {
            System.out.print("Role: ");
            role = scanner.next();
        } while (!EmployeeValidator.isRoleValid(role));

        double salary;
        do {
            System.out.print("Salary: ");
            salary = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
        } while (!EmployeeValidator.isSalaryValid(salary));

        LocalDate startDate;
        do {
            System.out.print("Start Date (YYYY-MM-DD): ");
            String startDateString = scanner.nextLine();
            startDate = LocalDate.parse(startDateString);
        } while (!EmployeeValidator.isStartDateValid(startDate));

        Employee employee = new Employee(name, department.getDepartment(), role, salary, startDate);
        if (service.create(employee)) {
            System.out.println("Employee added successfully!");
        }else{
            System.out.println("Employee was not added successfully!");
        }
    }


//in progress
//    private String validate(String output, Predicate<String> validationFunc, Scanner scanner){
//        String input;
//        do {
//            System.out.println(output);
//            input = scanner.nextLine();
//        }while (!validationFunc.test(input));
//
//        return input;
//    }

}
