package com.booking.system.repository;

import com.booking.system.models.Hotel;

import java.util.List;

//will be implemented later and the system will have more than one hotel
public class HotelRepository implements Repository<Hotel>{
    @Override
    public List<Hotel> getAll() {
        return null;
    }

    @Override
    public Hotel getById(int id) {
        return null;
    }

    @Override
    public boolean create(Hotel entity) {
        return true;
    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public void update(Hotel h) {

    }


}
