package com.example.FinalProject.utils;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class DateConverter {
    private static final String DATE_FORMAT_PATTERNS = """
            [MM/dd/yyyy]\
            [M/dd/yyyy]\
            [MM/d/yyyy]\
            [MM/dd/yyyy]\
            [dd/MM/yyyy]\
            [yyyy/MM/dd]\
            [MM-dd-yyyy]\
            [dd-MM-yyyy]\
            [yyyy-MM-dd]\
            [dd.MM.yyyy]\
            [dd.MM.yy]""";
            // Add more patterns when needed

    public static LocalDate convertStringToDate(String dateString) {
        if (dateString.equals("NULL")) {
            return LocalDate.now();
        }
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERNS);
            return LocalDate.parse(dateString, dtf);
        } catch (Exception e) {
            System.err.printf(("Invalid date format: %s" + dateString));
            throw e;
        }
    }
}
