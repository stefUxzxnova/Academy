package com.staffmanagement.system.utils.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

public class JsonFileCreator {
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

    private static String getPropertyValue(String propertyName){
        try {
            Properties properties = new Properties();
            InputStream inputStream = JsonFileCreator.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                return properties.getProperty(propertyName);
            } else {
                System.err.println("config.properties not found.");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while getting a property!");
            e.printStackTrace();
        }
        return null;
    }
}
