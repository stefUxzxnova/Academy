package com.inventory.system.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    public static String getPropertyValue(String propertyName){
        try {
            Properties properties = new Properties();
            InputStream inputStream = FileCreator.class.getClassLoader().getResourceAsStream("config.properties");
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
