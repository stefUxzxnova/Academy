package com.booking.system.services;

import java.time.LocalDate;
import java.util.Arrays;

public class Validator {
    public static boolean validateDates(LocalDate checkInDate, LocalDate checkOutDate){
        if (checkInDate.isAfter(checkOutDate) || checkInDate.isEqual(checkOutDate)) {
            return false;
        }
        return true;
    }
    public static LocalDate validateDate(String input){
        LocalDate date;
        try {
            int[] dateParts = Arrays.stream(input
                            .trim()
                            .split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            date = LocalDate.of(dateParts[2], dateParts[1], dateParts[0]);
        }catch (Exception e){
            System.out.println("Invalid date format!");
            return null;
        }
        if(date.isBefore(LocalDate.now())) {
            return null;
        }
        return date;
    }

    public static boolean isValidValue(String commandNumber){
        try {
            Integer.parseInt(commandNumber);
            return true;
        }catch (Exception e){
            System.out.println("Invalid command");
            return false;
        }
    }
}
