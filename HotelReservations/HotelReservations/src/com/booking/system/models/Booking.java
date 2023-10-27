package com.booking.system.models;

import java.util.Date;

public class Booking extends BaseEntity{
    private int roomNum;
    private int userId;
    private int days;
    private double oneDayPrice;
    private double totalPrice;
    private Date checkInDate;
    private Date checkOutDate;

    public Booking(int roomNum, int userId, double oneDayPrice,
                   Date checkInDate, Date checkOutDate) {
        super();
        this.roomNum = roomNum;
        this.userId = userId;
        this.days = calculateDays(checkInDate, checkOutDate);
        this.oneDayPrice = oneDayPrice;
        this.totalPrice = calculateTotalPrice(roomNum, days);
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    private int calculateDays(Date checkInDate, Date checkOutDate){
        long timeDifferenceMillis = checkOutDate.getTime() - checkInDate.getTime();
        long seconds = timeDifferenceMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return (int)hours / 24;
    }
    private int calculateTotalPrice(int oneDayPrice, int days){
        return oneDayPrice * days;
    }

    public int getRoomId() {
        return roomNum;
    }

    public void setRoomId(int roomId) {
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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return roomNum + " " + userId + " " + days + " "
                + oneDayPrice + " " + totalPrice + " " + checkInDate + " " + checkOutDate;
    }
}
