package com.booking.system.utils;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesManagement {
    public static File createFile(String fileName){
//        Properties properties = new Properties();
//        try(InputStream input = new FileInputStream("config.properties"))
//        {
//            properties.load(input);
//        } catch (IOException e) {
//            System.out.println("error occurred!");
//            return null;
//        }
        Path path = Path.of("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\HotelReservations\\");
        //String baseDirectory = properties.getProperty("reservationSystem.directory");

        //create file
        File file = new File(path + "\\" +fileName);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
        }catch (Exception e){
            System.out.println("error occurred!");
        }
        return file;
    }
    public static List<String[]> readFile(File file) {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader fr = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = fr.readLine()) != null) {
                String[] parts = Arrays.stream(line.split(", "))
                        .toArray(String[]::new);
                list.add(parts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static <T> boolean writeFile(File file, T[] lines) {
        try (OutputStream fw = new FileOutputStream(file, true)){
                String csvLine = convertToCSV(lines);
                fw.write(csvLine.getBytes());
        }catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static <T> boolean writeFileWithUpdates(File file, T[] lines) {
        try (OutputStream fw = new FileOutputStream(file)){
            String csvLine = convertToCSV(lines);
            fw.write(csvLine.getBytes());
        }catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private static <T> String convertToCSV(T[] entities) {
        List<String> list = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        for (T entity : entities) {
            String e = Stream.of(entity.toString().split(" ")).collect(Collectors.joining(", "));
            str.append(e + "\n");
        }
        return str.toString();
    }
}
