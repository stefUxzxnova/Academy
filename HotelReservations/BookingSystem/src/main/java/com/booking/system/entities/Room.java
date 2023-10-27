package com.booking.system.entities;

public class Room extends BaseEntity{
    private int roomNumber;
    private RoomType type;


    public Room(int id, int roomNumber, RoomType type) {
        super(id);
        this.roomNumber = roomNumber;
        this.type = type;
    }
    public Room(){}

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + this.getId() +
                ", roomNumber=" + roomNumber +
                ", type=" + type.getName() +
                '}';
    }
}
