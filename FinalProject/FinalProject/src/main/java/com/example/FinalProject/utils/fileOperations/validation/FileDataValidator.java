package com.example.FinalProject.utils.fileOperations.validation;

import com.example.FinalProject.utils.DateConverter;

public class FileDataValidator {

        public static boolean isEmployeeToProjectObjectDataValid(String[] data) {
            return validateId(data[0]) && validateId(data[1]) && validateDate(data[2]) && validateDate(data[3]);
        }
        private static boolean validateId(String id) {
            try {
                Long.parseLong(id);
                return true;
            } catch (NumberFormatException e) {
                System.err.println("Invalid id");
                return false;
            }
        }


        private static boolean validateDate(String dateString) {
            try {
                DateConverter.convertStringToDate(dateString);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

}
