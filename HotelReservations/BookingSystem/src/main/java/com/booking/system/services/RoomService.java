package com.booking.system.services;

import com.booking.system.entities.Hotel;
import com.booking.system.entities.Room;
import com.booking.system.entities.RoomType;
import com.booking.system.repos.RoomRepo;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RoomService {

    private RoomRepo roomRepo;
    private RoomTypeService roomTypeService;
    public RoomService(Hotel hotel) {
        roomRepo = new RoomRepo(hotel);
        roomTypeService = new RoomTypeService();
    }

    public List<Room> getAllRooms(){
        return roomRepo.getAll();
    }
    public Room getById(int id){
        return roomRepo.getById(id);
    }
    public Room getRoomByRoomNum(int roomNum){return roomRepo.getByRoomNum(roomNum);}
    public boolean createRoom( String input ){
        String[] roomDetails = Arrays.stream(input.trim().split(" ")).toArray(String[]::new);
        int roomNumber = Integer.parseInt(roomDetails[0]);
        //doesn't allow rooms with same numbers
        if (roomRepo.getAll().stream().filter(e -> e.getRoomNumber() == roomNumber).findFirst().orElse(null) != null) {
            return false;
        }
        RoomType type = roomTypeService.getById(Integer.parseInt(roomDetails[1]));
        //simulate autoincrement
        Room roomWithBiggestId = roomRepo.getAll().stream().max(Comparator.comparingInt(Room::getId)).orElse(null);
        int maxId = 0;
        if (roomWithBiggestId != null) {
            maxId = roomWithBiggestId.getId();
        }
        Room room = new Room(maxId+1, roomNumber, type);
        return roomRepo.create(room);
    }

    public boolean deleteRoom(int id){
        return roomRepo.delete(id);
    }
}
