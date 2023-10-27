package com.booking.system.repository;

import com.booking.system.models.Booking;
import com.booking.system.models.User;
import com.booking.system.utils.FilesManagement;

import java.io.File;
import java.util.List;

public class BookingRepository implements Repository<Booking>{
    private final File bookingFile;
    public BookingRepository() {
        bookingFile = FilesManagement.createFile("Bookings.txt");
    }
    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public Booking getById(int id) {
        return null;
    }

    @Override
    public boolean create(Booking entity) {
        return FilesManagement.writeFile(bookingFile, new Booking[]{entity});

    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public void update(Booking b) {
        List<Booking> bookingList= this.getAll();
        Booking b1 = bookingList.stream().filter(e -> e.getId() == b.getId())
                .findFirst().orElse(null);
        bookingList.remove(b1.getId());
        bookingList.add(b);
        FilesManagement.writeFileWithUpdates(bookingFile, bookingList.toArray());
    }


}
