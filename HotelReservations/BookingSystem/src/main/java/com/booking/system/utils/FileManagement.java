package com.booking.system.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileManagement {
    //if the file doesn't already exist, create it
    public static File createFile(String fileName){
        File file = new File(Path.of(getPropertyValue("file.path")) + "\\" + fileName);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
        }catch (Exception e){
            System.out.println("Error occurred while creating the file!");
        }
        return file;
    }

    private static String getPropertyValue(String property){
        try {
            Properties properties = new Properties();
            InputStream inputStream = FileManagement.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                return properties.getProperty(property);

            } else {
                System.err.println("config.properties not found.");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while getting a property!");
            e.printStackTrace();
        }
        return null;
    }

    public static <T> boolean writeJsonFile(File file, List<T> items){
        ObjectMapper objectMapper = new ObjectMapper();
        //javaTimeModule to serialize/deserialize the dates fields
        objectMapper.registerModule(new JavaTimeModule());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()))) {
            //convert object to json format
            objectMapper.writeValue(file, items);
        }
        catch (IOException e) {
            System.out.println("Data has not been written to the file.");
            return false;
        }
        return true;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static <T> List<T> backUpList(File file, Class<T> valueType){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try{
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));

        } catch (com.fasterxml.jackson.databind.exc.MismatchedInputException e) {
            System.out.println("MismatchedInputException: There is a mismatch between JSON data and the expected type.");
            e.printStackTrace();
            return null;
        }catch (IOException e){
            System.out.println("Error occurred during the backup.");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
