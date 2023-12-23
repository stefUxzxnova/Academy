package com.example.TodoApp.controller;

import com.example.TodoApp.dto.UserDTO;
import com.example.TodoApp.dto.UserMapper;
import com.example.TodoApp.model.User;
import com.example.TodoApp.service.UserService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  User user){
        //TODO validate data
        try {
            userService.register(user);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Success!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable  int id){
        //TODO validate data
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(UserMapper.userToUserDTO(user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}
