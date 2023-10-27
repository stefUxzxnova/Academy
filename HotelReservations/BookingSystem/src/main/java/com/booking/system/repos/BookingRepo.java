package com.booking.system.repos;

import com.booking.system.entities.Booking;
import com.booking.system.entities.Hotel;
import com.booking.system.utils.FileManagement;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class BookingRepo implements Repository{
    private final File bookingFile;
    private final List<Booking> bookingList;
    public BookingRepo(Hotel hotel){
        //if the file already exists the method will not create another
        bookingFile = FileManagement.createFile(hotel.getName() + "BookingFile.json");
        bookingList = FileManagement.backUpList(bookingFile, Booking.class);
    }
    @Override
    public List<Booking> getAll() {
       return bookingList;
    }

    @Override
    public Booking getById(int id) {
        return bookingList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean create(Object entity) {
        bookingList.add((Booking) entity);
        /*
         * when we create new instance we just append it to the json file */
        return FileManagement.writeJsonFile(bookingFile, bookingList);
    }

    @Override
    public boolean delete(int id) {
        Booking booking = bookingList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (booking != null) {
            bookingList.remove(id - 1);
        }
        /*
         * update the json file with the updated list */
        return FileManagement.writeJsonFile(bookingFile, bookingList);
    }

    @Override
    public boolean update(Object entity) {
        // TODO: 23.10.2023 г.
        return false;
    }

    public List<Booking> getAllBookingsForSpecifiedRoom(int... roomNumbers) {
        if (roomNumbers.length == 0) {
            // If no room numbers are provided, return all bookings
            return bookingList;
        }

        // Filter and return bookings for the specified room numbers
        return bookingList.stream()
                .filter(booking -> containsRoomNumber(roomNumbers, booking.getRoomNum()))
                .collect(Collectors.toList());
    }

    private boolean containsRoomNumber(int[] roomNumbers, int targetRoomNumber) {
        for (int roomNumber : roomNumbers) {
            if (roomNumber == targetRoomNumber) {
                return true;
            }
        }
        return false;
    }
}
