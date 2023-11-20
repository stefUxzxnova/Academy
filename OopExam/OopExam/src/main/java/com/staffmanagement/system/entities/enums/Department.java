package com.staffmanagement.system.entities.enums;

public enum Department {
    IT("IT"),
    ACCOUNTING("ACCOUNTING");

    private final String department;

    Department(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
