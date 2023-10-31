package com.inventory.system.repositories;

import com.inventory.system.entities.user.User;
import com.inventory.system.repositories.interfaces.Repository;
import com.inventory.system.utils.FileCreator;
import com.inventory.system.utils.FileReader;
import com.inventory.system.utils.FileWriter;

import java.io.File;
import java.util.List;

public class UsersRepo implements Repository {
    private static File usersFile;

    public UsersRepo() {
        usersFile = FileCreator.createFile("users.json");
    }

    @Override
    public List<User> getAll() {
        return FileReader.readFile(usersFile, User.class);
    }

    @Override
    public User getById(long id) {
        List<User> list = FileReader.readFile(usersFile, User.class);
        return list.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User create(Object entity) {
        List<User> list = FileReader.readFile(usersFile, User.class);
        list.add((User) entity);
        if (FileWriter.writeJsonFile(usersFile, list)) {
            return (User) entity;
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        List<User> list = FileReader.readFile(usersFile, User.class);
        User user = list.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (user != null) {
            list.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Object entity) {
        List<User> list = FileReader.readFile(usersFile, User.class);
        int index = list.indexOf(entity);
        if (index != -1) {
            list.set(index, (User) entity);
            /*
             * update the json file with the updated list */
            return FileWriter.writeJsonFile(usersFile, list);
        } else {
            // if user with the given id is not found
            return false;
        }
    }
}
