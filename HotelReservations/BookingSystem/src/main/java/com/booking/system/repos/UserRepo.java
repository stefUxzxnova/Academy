package com.booking.system.repos;

import com.booking.system.entities.Booking;
import com.booking.system.entities.Room;
import com.booking.system.entities.User;
import com.booking.system.enums.UserRoles;
import com.booking.system.utils.FileManagement;

import java.io.File;
import java.util.List;

public class UserRepo implements Repository{
    private final File usersFile;
    private final List<User> usersList;
    User amdinUser = new User(1, "admin", "adminpass","admin", UserRoles.ADMIN.getDisplayName(), 0);

    public UserRepo() {
        usersFile = FileManagement.createFile("users.json");
        usersList = FileManagement.backUpList(usersFile, User.class);
        /*
        * if we use the system for the first time, and we don't have anything to back up
        * (admin will be the first user) */
        if (usersList.isEmpty()) {
            usersList.add(amdinUser);
            FileManagement.writeJsonFile(usersFile, usersList);
        }
    }

    @Override
    public List<User> getAll() {
        return usersList;
    }

    @Override
    public User getById(int id) {
        return usersList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean create(Object entity) {
        usersList.add((User) entity);
        /*
         * when we create new instance we just append it to the json file */
        return FileManagement.writeJsonFile(usersFile, usersList);
    }

    @Override
    public boolean delete(int id) {
        User user = usersList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (user != null) {
            /*we subtract 1 because the ids of the users start from 1, but in the list they start from 0*/
            usersList.remove(id - 1);
        }
        /*
         * update the json file with the updated list */
        return FileManagement.writeJsonFile(usersFile, usersList);
    }

    @Override
    public boolean update(Object entity) {
        int index = usersList.indexOf(entity);
        if (index != -1) {
            usersList.set(index, (User) entity);
            /*
             * update the json file with the updated list */
            return FileManagement.writeJsonFile(usersFile, usersList);
        } else {
            // if roomType with the given id is not found
            return false;
        }
    }

    public User getUserByUserNameAndPassword(String username, String hashedPassword){
        return usersList.stream().filter(e -> e.getUsername().equals(username) &&
                e.getPassword().equals(hashedPassword)).findFirst().orElse(null);
    }

    // Add a booking to a user's booking history
    public void addToBookingHistory(int id, Booking booking){
        User user = usersList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        int indexOf = usersList.indexOf(user);
        user.getBookingHistory().add(booking);
        usersList.set(indexOf,user);
        FileManagement.writeJsonFile(usersFile, usersList);
    }

    // Remove a booking from a user's booking history and update cancellation fees
    public void removeFromBookingHistory(int id, Booking booking, Room room){
        User user = usersList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        int indexOf = usersList.indexOf(user);
        user.getBookingHistory().remove(booking);
        user.setCancellationFees(user.getCancellationFees() + room.getType().getCancellationFee());
        usersList.set(indexOf,user);
        FileManagement.writeJsonFile(usersFile, usersList);
    }
}
