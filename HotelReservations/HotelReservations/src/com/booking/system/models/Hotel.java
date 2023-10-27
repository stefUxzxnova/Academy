package com.booking.system.models;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends BaseEntity{
    private String name;
    private String city;
    private int ratingStars;
    private List<Room> rooms;

    public Hotel(String name, String city, int ratingStars, List<Room> rooms) {
        super();
        this.name = name;
        this.city = city;
        this.ratingStars = ratingStars;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(int ratingStars) {
        this.ratingStars = ratingStars;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
