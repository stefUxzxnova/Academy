package com.example.TodoApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    @NotEmpty
    private String username;
    @Size(min = 8, max = 12)
    private String password;
    @Email(message = "Email is not valid")
    private String email;
}
