package com.booking.system.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity{
    private String username;
    private String password;
    private String name;
    private String role;
    private double cancellationFees;
    private List<Booking> bookingHistory;

    public User(){}
    public User(int id, String username, String password, String name, String role, double cancellationFees) {
        super(id);
        this.username = username;
        this.password = hashPassword(password);
        this.name = name;
        this.role = role;
        this.cancellationFees = cancellationFees;
        this.bookingHistory = new ArrayList<>();
    }

//    public User(int id, String username, String password, String name, String role, List<Booking> list) {
//        this.id = id;
//        this.username = username;
//        this.password = hashPassword(password);
//        this.name = name;
//        this.role = role;
//        this.bookingHistory = list;
//    }

    //method that simulates hash function
    public static String hashPassword(String password){
        int hash = 0;
        for (char c : password.toCharArray()) {
            hash = (hash * 31) + c;
        }
        return Integer.toString(hash);
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCancellationFees() {
        return cancellationFees;
    }

    public void setCancellationFees(double cancellationFees) {
        this.cancellationFees = cancellationFees;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", cancellationFees=" + cancellationFees +
                ", bookingHistory=" + bookingHistory +
                '}';
    }
}
