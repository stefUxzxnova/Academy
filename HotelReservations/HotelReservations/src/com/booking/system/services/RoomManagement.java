package com.booking.system.services;

import com.booking.system.enums.UserRoles;
import com.booking.system.models.Hotel;
import com.booking.system.models.Room;
import com.booking.system.models.User;
import com.booking.system.repository.RoomRepository;
import com.booking.system.repository.UserRepository;
import com.booking.system.utils.FilesManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RoomManagement {
    RoomRepository roomRepo;
    public RoomManagement(Hotel hotel) {
        roomRepo = new RoomRepository(hotel);
    }

    public List<Room> getAllRooms(){
        return roomRepo.getAll();
    }

    public Room getInfo(int roomNum){
        return roomRepo.getById(roomNum);
    }

    public boolean createRoom(Room room){
        return roomRepo.create(room);
    }
    public boolean deleteRoom(int roomId){
         return roomRepo.delete(roomId);
    }
}
