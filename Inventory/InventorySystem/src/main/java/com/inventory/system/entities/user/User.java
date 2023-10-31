package com.inventory.system.entities.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inventory.system.entities.order.Order;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static long uid = 1;
    @JsonProperty
    private long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private UserCredentials userCredentials;
    @JsonProperty
    private String email;
    @JsonProperty
    private String address;

    @JsonProperty
    private String role;

    @JsonProperty
    private List<Order> ordersHistory;

    public User(String name, UserCredentials userCredentials, String email, String address, String role) {
        this.id = uid++;
        this.name = name;
        this.userCredentials = userCredentials;
        this.email = email;
        this.address = address;
        this.role = role;
        this.ordersHistory = new ArrayList<>();
    }
    public User(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userCredentials=" + userCredentials +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
