package com.booking.system.repos;

import com.booking.system.entities.RoomType;
import com.booking.system.utils.FileManagement;
import java.io.File;
import java.util.List;

public class RoomTypeRepo implements Repository{
    private final File roomTypeFile;
    private final List<RoomType> roomTypesList;
    private List<String> amenities = List.of("TV", "jacuzzi" );
    RoomType roomTypeDeluxe = new RoomType(1, "Deluxe", 3, 120, 35.5, amenities);
    RoomType roomTypeSuite = new RoomType(2, "Suite", 3, 100, 30, amenities);
    RoomType roomTypeApartment = new RoomType(3, "Apartment", 3, 200, 50.5, amenities);
    RoomType roomTypeSingle = new RoomType(4, "Single", 1, 80, 10, amenities);
    public RoomTypeRepo() {
        roomTypeFile = FileManagement.createFile("roomTypes.json");
        roomTypesList = FileManagement.backUpList(roomTypeFile, RoomType.class);
        if (roomTypesList.isEmpty()) {
            roomTypesList.add(roomTypeDeluxe);
            roomTypesList.add(roomTypeSuite);
            roomTypesList.add(roomTypeApartment);
            roomTypesList.add(roomTypeSingle);
            FileManagement.writeJsonFile(roomTypeFile, roomTypesList);
        }

    }


    @Override
    public List<RoomType> getAll() {
        return roomTypesList;
    }

    @Override
    public RoomType getById(int id) {
        return roomTypesList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean create(Object entity) {
        roomTypesList.add((RoomType) entity);
        return FileManagement.writeJsonFile(roomTypeFile, roomTypesList);
    }

    @Override
    public boolean delete(int id) {
        RoomType roomType = roomTypesList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (roomType != null) {
            roomTypesList.remove(id);
        }
        /*
        * update the json file with the updated list */
        return FileManagement.writeJsonFile(roomTypeFile, roomTypesList);
    }

    @Override
    public boolean update(Object entity) {
        int index = roomTypesList.indexOf(entity);
        if (index != -1) {
            roomTypesList.set(index, (RoomType) entity);
            /*
             * update the json file with the updated list */
            return FileManagement.writeJsonFile(roomTypeFile, roomTypesList);
        } else {
            // if roomType with the given id is not found
            return false;
        }
    }
}
