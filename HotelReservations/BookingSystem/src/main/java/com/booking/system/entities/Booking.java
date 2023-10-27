package com.booking.system.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Booking extends BaseEntity {
    private int roomNum;
    private int userId;
    private double oneDayPrice;
    private double totalPrice;
    private int days;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Booking(){}
    public Booking(int id, int roomNum, int userId, double oneDayPrice,
                   LocalDate checkInDate, LocalDate checkOutDate) {
        super(id);
        this.roomNum = roomNum;
        this.userId = userId;
        this.oneDayPrice = oneDayPrice;
        this.days = calculatePeriodOFBooking(checkInDate, checkOutDate);
        this.totalPrice = calculateTotalPrice(oneDayPrice, days);
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    private double calculateTotalPrice(double oneDayPrice, int days){
        return oneDayPrice * days;
    }
    private int calculatePeriodOFBooking(LocalDate checkInDate, LocalDate checkOutDate){
        return Period.between(checkInDate, checkOutDate).getDays();
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomId) {
        this.roomNum = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Booking{id=" + this.getId() +
                ", roomNum=" + roomNum +
                ", userId=" + userId +
                ", oneDayPrice=" + oneDayPrice +
                ", totalPrice=" + totalPrice +
                ", days=" + days +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
