package com.inventory.system.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DirectoryManager {
    public static List<File> listAllFilesInDirectory(String propertyName){
        String url = PropertiesManager.getPropertyValue(propertyName);
        File directory = new File(url);
        if (directory.exists() && directory.isDirectory()) {
            List<File> files = Arrays.stream(directory.listFiles()).toList();
            return files;
        }
        return new ArrayList<>();
    }

}
