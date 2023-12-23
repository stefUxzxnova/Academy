package com.example.FinalProject.controller;

import com.example.FinalProject.model.Employee;
import com.example.FinalProject.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> list = employeeService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try {
            Employee employee = employeeService.findById(id);
            return ResponseEntity.ok(employee);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Invalid id:" + id);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult){
        return saveEmployee(employee, bindingResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult){
        return saveEmployee(employee, bindingResult);
    }

    private ResponseEntity<?> saveEmployee(Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid input. Please check the provided data.");
        }
        try{
            employeeService.save(employee);
            return ResponseEntity.ok(employee);
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error creating employee: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> editEmployee(@PathVariable Long id){
        try {
            employeeService.delete(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input. " + e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error deleting employee: " + e.getMessage());
        }

    }
}

