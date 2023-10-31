package com.inventory.system.services;

import com.inventory.system.entities.user.User;
import com.inventory.system.entities.user.UserCredentials;
import com.inventory.system.repositories.UsersRepo;

import java.util.List;

public class UsersManager {
    private UsersRepo userRepository;
    public UsersManager() {
        this.userRepository = new UsersRepo();
    }
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }
    public User getById(long id) {
        return userRepository.getById(id);
    }
    public User getLoggedUser(String username, String password){
        List<User> allUsers = userRepository.getAll();
        String hashedPassword = UserCredentials.hashPassword(password);
        User user = allUsers.stream()
                .filter(u -> u.getUserCredentials().getUsername().equals(username)
                        && u.getUserCredentials().getPassword().equals(hashedPassword))
                .findFirst()
                .orElse(null);
        return user;
    }

    public User createUser(String name, String username, String password, String address, String email, String role) {
        // Validate the user data, e.g., check if required fields are provided
        if (!Validator.isValidEmail(email)
                || !Validator.isValidPassword(password)
                || !Validator.isValidUser(username)) {
            return null;
        }
        User createdUser = userRepository.create(new User(
                name,
                new UserCredentials(
                        username,
                        password
                ),
                email,
                address,
                role
        ));
        if (createdUser != null) {
            return createdUser;
        }
        return null;
    }

    public User add(User user){
        return userRepository.create(user);
    }
    public boolean updateUser(User updatedUser) {
        // TODO: 31.10.2023 г.
        return true;
    }

    public boolean deleteUser(long userId) {
        return userRepository.delete(userId);
    }


}
