package com.booking.system.services;

import com.booking.system.entities.RoomType;
import com.booking.system.repos.RoomTypeRepo;
import java.util.List;

public class RoomTypeService {
    private static RoomTypeRepo roomTypeRepo;
    public RoomTypeService() {
        roomTypeRepo = new RoomTypeRepo();
    }

    public List<RoomType> getAll(){
        return roomTypeRepo.getAll();
    }
    public RoomType getById(int id){
        if (id <= 0) {
            return null;
        }
        return roomTypeRepo.getById(id);
    }
    public boolean deleteRoomType(int id) {
        if (id <= 0) {
            return false;
        }
        return roomTypeRepo.delete(id);
    }

}
