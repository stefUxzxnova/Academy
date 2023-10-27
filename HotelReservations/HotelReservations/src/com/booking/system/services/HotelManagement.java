package com.booking.system.services;

import com.booking.system.enums.UserRoles;
import com.booking.system.models.Hotel;
import com.booking.system.models.Room;
import com.booking.system.models.User;
import com.booking.system.repository.HotelRepository;
import com.booking.system.repository.UserRepository;

import java.util.List;

//will be implemented later and the system will have more than one hotel
public class HotelManagement {
    HotelRepository hotelRepo = new HotelRepository();
    public List<Hotel> getAllHotels(){
        return hotelRepo.getAll();
    }

    public Hotel getInfo(int roomId){
        return hotelRepo.getById(roomId);
    }

    public boolean createHotel(){
        return true;
    }
    public boolean deleteHotel(){
        return false;
    }

}
