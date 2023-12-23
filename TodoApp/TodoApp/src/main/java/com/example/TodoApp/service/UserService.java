package com.example.TodoApp.service;

import com.example.TodoApp.model.User;
import com.example.TodoApp.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public void register(User user){
        //TODO add some validation rules
        //password encrypt
        userRepo.save(user);
    }


    public User getUserById(int id) {
        return userRepo.getUserById(id);
    }
}
