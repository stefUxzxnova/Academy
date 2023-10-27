package com.booking.system.entities;

import java.util.List;

public class RoomType extends BaseEntity{
    private String name;
    private int maxOccupancy;
    private double pricePerNight;
    private double cancellationFee;
    private List<String> amenities;

    public RoomType(int id, String name, int maxOccupancy, double pricePerNight, double cancellationFee, List<String> amenities) {
        super(id);
        this.name = name;
        this.maxOccupancy = maxOccupancy;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.amenities = amenities;
    }

    public RoomType() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
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

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "RoomType{ id=" + this.getId() +
                ", name='" + name + '\'' +
                ", maxOccupancy=" + maxOccupancy +
                ", pricePerNight=" + pricePerNight +
                ", cancellationFee=" + cancellationFee +
                ", amenities=" + amenities +
                '}';
    }
}
