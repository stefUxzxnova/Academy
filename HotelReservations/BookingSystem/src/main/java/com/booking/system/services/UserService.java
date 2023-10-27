package com.booking.system.services;

import com.booking.system.entities.Booking;
import com.booking.system.entities.Room;
import com.booking.system.entities.User;
import com.booking.system.enums.UserRoles;
import com.booking.system.repos.UserRepo;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


public class UserService {
    UserRepo userRepo = new UserRepo();
    public List<User> getAllUsers(){
        return userRepo.getAll();
    }
    public User getById(int id){
        return userRepo.getById(id);
    }
    public boolean createUser(String username, String password, String name, String role, double cancellationFees){
        User userWithBiggestId = userRepo.getAll().stream().max(Comparator.comparingInt(User::getId)).orElse(null);
        User user = new User(userWithBiggestId.getId()+1, username, password, name, role, cancellationFees);
        return userRepo.create(user);
    }

    public boolean deleteUser(int id){
        return userRepo.delete(id);
    }

//    public boolean createAdmin(User adminUser){
//        adminUser.setRole(UserRoles.ADMIN.getDisplayName());
//        return userRepo.create(adminUser);
//    }

    public User getLoggedUser(String username, String password){
        String hashedPassword = User.hashPassword(password);
        return userRepo.getUserByUserNameAndPassword(username, hashedPassword);
    }
    public boolean isAdmin(int loggedUserId){
        User user = userRepo.getById(loggedUserId);
        if (user.getRole().equals(UserRoles.ADMIN.getDisplayName())) {
            return true;
        }
        return false;
    }

    public List<Booking> getAllPresentOrFutureBookings(int loggedUserId){
        List<Booking> bookingHistory = userRepo
                .getById(loggedUserId)
                .getBookingHistory()
                    .stream()
                    .filter(e -> e.getCheckInDate().isEqual(LocalDate.now())
                                || e.getCheckInDate().isAfter(LocalDate.now()))
                    .toList();
        return bookingHistory;
    }

    public void addToBookingHistory(int userId, Booking booking){
        userRepo.addToBookingHistory(userId, booking);
    }
    public void removeFromBookingHistory(int userId, Booking booking, Room room){
        userRepo.removeFromBookingHistory(userId, booking, room);
    }
}
