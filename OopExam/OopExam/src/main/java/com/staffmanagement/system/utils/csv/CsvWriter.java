package com.staffmanagement.system.utils.csv;

import com.opencsv.CSVWriter;
import com.staffmanagement.system.entities.Employee;
import com.staffmanagement.system.utils.interfaces.Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class CsvWriter implements Writer {
    private final File file;

    public CsvWriter(File file) {
        this.file = file;
    }

//    public void writeItemsToCsv(Map<Long, Employee> employees) {
//        try (CSVWriter writer = new CSVWriter(new FileWriter(file.getPath()))) {
//            String[] header = {"Id", "Name", "StartDate", "Department", "Role", "Salary"};
//            writer.writeNext(header);
//            for (Employee employee : employees.values()) {
//                // Customize the CSV writing based on the item type
//                String[] line = new String[]{
//                        String.valueOf(employee.getId()),
//                        employee.getName(),
//                        employee.getDepartment(),
//                        employee.getRole(),
//                        String.valueOf(employee.getSalary()),
//                        String.valueOf(employee.getStartDate())};
//                writer.writeNext(line);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public <T> boolean writeFile(List<T> data) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file.getPath()))) {
            Field[] fields = data.get(0).getClass().getDeclaredFields();
            // Write the header
            String[] header = new String[fields.length - 1];
            for (int i = 1; i < fields.length; i++) {
                header[i - 1] = fields[i].getName();
            }
            csvWriter.writeNext(header);

            // Write the data
            for (T item : data) {
                String[] row = new String[fields.length];
                for (int i = 1; i < fields.length ; i++) {
                    fields[i].setAccessible(true);
                    try {
                        Object value = fields[i].get(item);
                        row[i] = value != null ? value.toString() : "";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                csvWriter.writeNext(row);
            }

            System.out.println("CSV file written successfully!");
            return true;
        } catch (IOException e) {
            System.out.println("Error occurred while writing");
            return false;
        }
    }
}
