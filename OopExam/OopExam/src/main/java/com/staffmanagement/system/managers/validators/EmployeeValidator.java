package com.staffmanagement.system.managers.validators;

import com.staffmanagement.system.entities.enums.Department;

import java.time.LocalDate;

public class EmployeeValidator {
    public static boolean isNameValid(String name) {
        return name != null && !name.isBlank();
    }

    public static boolean isIdValid(String id) {
       try {
           Long.parseLong(id);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    public static boolean isDepartmentValid(Department department) {
        return department != null;
    }

    public static boolean isRoleValid(String role) {
        return role != null && !role.isBlank();
    }

    public static boolean isSalaryValid(double salary) {
        return salary >= 0;
    }

    public static boolean isStartDateValid(LocalDate startDate) {
        return startDate != null;
    }
}
