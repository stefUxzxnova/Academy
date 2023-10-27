package com.booking.system.services;

import com.booking.system.enums.RoomStatus;
import com.booking.system.models.Booking;
import com.booking.system.models.Room;
import com.booking.system.models.User;
import com.booking.system.repository.BookingRepository;
import com.booking.system.repository.RoomRepository;
import com.booking.system.repository.UserRepository;
import com.booking.system.utils.FilesManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingManagement {
    BookingRepository bookingRepo = new BookingRepository();
    RoomRepository roomRepository = new RoomRepository();
    UserRepository userRepository = new UserRepository();
    private final File bookingsFile;
    private static List<Booking> listBookings;
    public BookingManagement() {
        bookingsFile = FilesManagement.createFile("Bookings.txt");
        listBookings = new ArrayList<>();
    }

    public List<Booking> getAllBookings(){
        return bookingRepo.getAll();
    }

    public Booking getInfo(int bookingId){
        return bookingRepo.getById(bookingId);
    }

    public boolean createBooking(int roomN, int loggedUserId, int[] checkInDateElements, int[] checkOutDateElements){
        Booking booking = null;
        //validation
        try {
            int roomNum = roomN;
            Date checkInDate = new Date(checkInDateElements[0], checkInDateElements[1], checkInDateElements[2]);
            Date checkOutDate = new Date(checkOutDateElements[0], checkOutDateElements[1], checkOutDateElements[2]);
            Room room;
            if ((room = checkIfExists(roomNum))!= null) {
                booking = new Booking(roomNum, loggedUserId, room.getPricePerNight(), checkInDate, checkOutDate);
            }
        }catch (Exception e){
            return false;
        }
        if (bookingRepo.create(booking)) {
            Room room = roomRepository.getById(roomN);
            room.setStatus(RoomStatus.BOOKED.getDisplayName());
            roomRepository.update(room);
            User user = userRepository.getById(loggedUserId);
            user.getBookingHistory().add(booking);
            userRepository.update(user);
            return true;
        }

        return false;
    }
    public boolean deleteBooking(int bookingId){
        return bookingRepo.delete(bookingId);
    }

    private Room checkIfExists(int roomNum){
        RoomRepository roomRepository = new RoomRepository();
        return roomRepository.getById(roomNum);
    }
}
