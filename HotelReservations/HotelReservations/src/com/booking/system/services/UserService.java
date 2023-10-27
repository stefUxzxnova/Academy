package com.booking.system.services;

import com.booking.system.enums.UserRoles;
import com.booking.system.models.User;
import com.booking.system.repository.UserRepository;

import java.util.List;

public class UserService {
    UserRepository userRepo = new UserRepository();
    public List<User> getAllUsers(int loggedUserId){
        User user = userRepo.getById(loggedUserId);
        if (user != null) {
            if (user.getRole().equals(UserRoles.ADMIN)) {
                return userRepo.getAll();
            }
        }
        return null;
    }

    public User getInfo(int loggedUser){
        User user = userRepo.getById(loggedUser);
        if (user != null) {
            return userRepo.getById(loggedUser);
        }
        return null;
    }

    public boolean deleteUser(int loggedUser){
        User user = userRepo.getById(loggedUser);
        if (user != null) {
            return userRepo.delete(loggedUser);
        }
        return false;
    }

    public boolean createUser(String[] userInput){
        User user;
        //validation
       try {
           int age = Integer.parseInt(userInput[3]);
           user = new User(userInput[0], userInput[1], userInput[2], age);
       }catch (Exception e){
           return false;
       }
        return userRepo.create(user);
    }

    public boolean addAdmin(User admin){
        return userRepo.create(admin);
    }

    public User getById(int id){
        return userRepo.getById(id);
    }
    public User getLoggedUser(String username, String password){
        return userRepo.getLoggedUser(username, password);
    }

}
