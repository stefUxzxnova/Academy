package com.booking.system.models;

import com.booking.system.enums.UserRoles;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity{
    private String username;
    private String password;
    private String name;
    private int age;
    private String role;
    private final List<Booking> bookingHistory;

    public User(String username, String password, String name, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.role = UserRoles.REGISTEREDUSER.getDisplayName();
        this.bookingHistory = new ArrayList<>();
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.bookingHistory = new ArrayList<>();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return username + " " + password + " " + name + " " + age;
    }
}
