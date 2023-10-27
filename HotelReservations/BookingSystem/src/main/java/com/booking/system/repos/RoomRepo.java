package com.booking.system.repos;

import com.booking.system.entities.Hotel;
import com.booking.system.entities.Room;
import com.booking.system.utils.FileManagement;

import java.io.File;
import java.util.List;

public class RoomRepo implements Repository{
    private final File roomsFile;
    private final Hotel hotel;
    public RoomRepo(Hotel hotel){
        this.hotel = hotel;
        roomsFile = FileManagement.createFile(hotel.getName() + "RoomsFile.json");
        List<Room> list = FileManagement.backUpList(roomsFile, Room.class);
        if (hotel.getRooms().isEmpty()) {
            for (Room r : list) {
                hotel.getRooms().add(r);
            }
        }

    }
    @Override
    public List<Room> getAll() {
        return hotel.getRooms();
    }

    @Override
    public Room getById(int id) {
        return hotel.getRooms().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean create(Object entity) {
        hotel.getRooms().add((Room) entity);
        return FileManagement.writeJsonFile(roomsFile, hotel.getRooms());
    }

    @Override
    public boolean delete(int id) {
        Room room = hotel.getRooms().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (room != null) {
            /*we subtract 1 because the ids of the users start from 1, but in the list they start from 0*/
            hotel.getRooms().remove(id - 1);
        }
        /*
         * update the json file with the updated list */
        return FileManagement.writeJsonFile(roomsFile, hotel.getRooms());

    }

    @Override
    public boolean update(Object entity) {
return false;
    }
    public Room getByRoomNum(int roomNum) {
        return hotel.getRooms().stream().filter(e -> e.getRoomNumber() == roomNum).findFirst().orElse(null);
    }
}
