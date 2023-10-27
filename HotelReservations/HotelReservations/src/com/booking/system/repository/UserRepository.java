package com.booking.system.repository;

import com.booking.system.enums.UserRoles;
import com.booking.system.models.User;
import com.booking.system.utils.FilesManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    /**
     * we will use usersFile as database*/
    private final File usersFile;

    public UserRepository() {
        usersFile = FilesManagement.createFile("Users.txt");
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        List<String[]> list = FilesManagement.readFile(usersFile);
        for (var userLine : list) {
            String username = userLine[0];
            String password = userLine[1];
            String name = userLine[2];
            int age = Integer.parseInt(userLine[3]);
            User user = new User(username, password, name, age);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getById(int id) {
        List<User> users = new ArrayList<>();
        List<String[]> list = FilesManagement.readFile(usersFile);
        for (String[] userLine : list) {
            try {
                String username = userLine[0];
                String password = userLine[1];
                String name = userLine[1];
                int age = Integer.parseInt(userLine[1]);
                User user = new User(username, password, name, age);
                users.add(user);
            } catch (Exception e) {
                return null;
            }
        }
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean create(User user) {
        setUserRole(user);
        return FilesManagement.writeFile(usersFile, new User[]{user});
    }

    private void setUserRole(User user){
        if (user.getRole() == null) {
            user.setRole(UserRoles.REGISTEREDUSER.getDisplayName());
        }
    }


    @Override
    public boolean delete(int id) {
        List<User> list = getAll();
        User user = getById(id);
        if (user != null) {
            list.remove(user.getId());
        }
        return FilesManagement.writeFile(usersFile, list.stream().toArray());
    }

    @Override
    public void update(User u) {
        List<User> userList = this.getAll();
        User b1 = userList.stream().filter(e -> e.getId() == u.getId())
                .findFirst().orElse(null);
        userList.remove(b1.getId());
        userList.add(u);
        FilesManagement.writeFileWithUpdates(usersFile, userList.toArray());
    }

    public User getLoggedUser(String username, String password) {
        List<User> users = this.getAll();
        return users.stream()
                .filter(e -> e.getUsername().equals(username)
                        && e.getPassword().equals(password))
                .findFirst().orElse(null);
    }

}
