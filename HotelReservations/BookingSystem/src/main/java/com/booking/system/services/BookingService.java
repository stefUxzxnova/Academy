package com.booking.system.services;

import com.booking.system.entities.Booking;
import com.booking.system.entities.Hotel;
import com.booking.system.entities.Room;
import com.booking.system.entities.User;
import com.booking.system.repos.BookingRepo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookingService {
    private BookingRepo bookingRepo;
    private RoomService roomService;
    private UserService userService;
    public BookingService(Hotel hotel){
        bookingRepo = new BookingRepo(hotel);
        roomService = new RoomService(hotel);
        userService = new UserService();
    }

    public List<Booking> getAll(){
        return bookingRepo.getAll();
    }
    public Booking getById(int id){
        return bookingRepo.getById(id);
    }
    public List<Booking> getAllBookingsForSpecifiedRoom(int... roomNumbers){
        return bookingRepo.getAllBookingsForSpecifiedRoom(roomNumbers);
    }

    public boolean create( int roomNum, int userId, double oneDayPrice,
                          LocalDate checkInDate, LocalDate checkOutDate ){
        //simulate autoincrement
        Booking lastBooking = bookingRepo.getAll().stream().max(Comparator.comparingInt(Booking::getId)).orElse(null);
        int maxId = 0;
        if (lastBooking != null) {
            maxId = lastBooking.getId();
        }
        Booking booking = new Booking(maxId + 1, roomNum, userId, oneDayPrice, checkInDate, checkOutDate);
        userService.addToBookingHistory(userId, booking);
        return bookingRepo.create(booking);
    }

    public boolean cancelBooking(int bookingId){
        if (bookingId <= 0) {
            return false;
        }
        Booking booking = bookingRepo.getById(bookingId);
        User user = userService.userRepo.getById(bookingRepo.getById(bookingId).getUserId());
        Room room = roomService.getById(booking.getRoomNum());
        userService.removeFromBookingHistory(user.getId(), booking, room);
        return bookingRepo.delete(bookingId);
    }

    public List<Room> getAvailableRoomsForGivenDates(LocalDate checkInDate, LocalDate checkOutDate){
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : roomService.getAllRooms()) {
            if (checkAvailability(checkInDate, checkOutDate, room.getRoomNumber())) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean checkAvailability(LocalDate checkInDate, LocalDate checkOutDate, int roomNum){
        List<Booking> listOfBookings = bookingRepo.getAllBookingsForSpecifiedRoom(roomNum);
        if (listOfBookings.isEmpty()) {
            return true;
        }
        for (Booking booking : listOfBookings) {
            /* if we have booking for the given dates */
            if (dateMatchingCheck(booking.getCheckInDate(), booking.getCheckOutDate(), checkInDate, checkOutDate)) {
                   return false;
            }
        }
        return true;
    }

    private boolean dateMatchingCheck(LocalDate bookingCheckInDate, LocalDate bookingCheckOutDate, LocalDate wantedCheckOutDate, LocalDate wantedCheckInDate) {
        // Check if the wanted check-in date is between the already existing booking
        if (wantedCheckInDate.isAfter(bookingCheckInDate) && wantedCheckInDate.isBefore(bookingCheckOutDate)) {
            return true;
        }
        // Check if the wanted check-out date is between the already existing booking
        if (wantedCheckOutDate.isAfter(bookingCheckInDate) && wantedCheckOutDate.isBefore(bookingCheckOutDate)) {
            return true;
        }
        // Check if the existing booking is completely contained within the wanted booking
        if (bookingCheckInDate.isAfter(wantedCheckInDate) && bookingCheckOutDate.isBefore(wantedCheckOutDate)) {
            return true;
        }
        // Check if the existing booking completely contains the wanted booking
        if (wantedCheckInDate.isAfter(bookingCheckInDate) && wantedCheckOutDate.isBefore(bookingCheckOutDate)) {
            return true;
        }
        return false;
    }
}
