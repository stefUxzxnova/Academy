package com.booking.system.repository;

import com.booking.system.enums.UserRoles;
import com.booking.system.models.Hotel;
import com.booking.system.models.Room;
import com.booking.system.models.User;
import com.booking.system.utils.FilesManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements Repository<Room>{
    private final File roomFile;
    public RoomRepository(Hotel hotel) {
        roomFile = FilesManagement.createFile("Rooms.txt");
    }
    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        List<String[]> listRooms = FilesManagement.readFile(roomFile);
        for (var room : listRooms) {
            try {
                int roomNumber = Integer.parseInt(room[0]);
                String type = room[1];
                double pricePerNight = Double.parseDouble(room[2]);
                double cancellationFee = Double.parseDouble(room[3]);
                String status = room[4];
                int maxOccupancy = Integer.parseInt(room[5]);
                List<String> amenities = new ArrayList<>();
                for (int i = 6; i < room.length; i++) {
                    amenities.add(room[i]);
                }
                Room r = new Room(roomNumber, type, pricePerNight, cancellationFee, status, maxOccupancy, amenities);
                rooms.add(r);
            }catch (Exception e){
                return null;
            }
        }
        return rooms;
    }

    @Override
    public Room getById(int roomNum) {
        List<Room> rooms = this.getAll();
        return rooms.stream().filter(e -> e.getRoomNumber() == roomNum)
                .findFirst().orElse(null);
    }

    @Override
    public boolean create(Room entity) {
        return FilesManagement.writeFile(roomFile, new Room[]{entity});
    }

    @Override
    public boolean delete(int id) {
        List<Room> list = this.getAll();
        Room room = this.getById(id);
        if (room != null) {
            list.remove(room.getId());
        }
        return FilesManagement.writeFile(roomFile, list.stream().toArray());

    }

    @Override
    public void update(Room room) {

    }


}
