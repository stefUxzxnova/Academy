package com.example.FinalProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @Column(nullable = false, unique = true)
    @Size(min = 10)
    private String telephone;

    public Employee(Long id, String firstName, String lastName, String telephone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
