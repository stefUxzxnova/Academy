package com.booking.system.models;

import java.util.ArrayList;
import java.util.List;

public class Room extends BaseEntity {

    private int roomNumber;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private String status;
    private int maxOccupancy;
    private final List<String> amenities;

    public Room(int roomNumber, String type, double pricePerNight, double cancellationFee,
                String status, int maxOccupancy, List<String> amenities) {
        super();
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.status = status;
        this.maxOccupancy = maxOccupancy;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return roomNumber + " " + type + " " + pricePerNight + " "
                + cancellationFee + " " + status + " " + maxOccupancy + " " + amenities;
    }
}
