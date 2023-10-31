package com.inventory.system.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DirectoryManager {
    public static List<String> listAllFileNamesInDirectory(String propertyName){
        String url = PropertiesManager.getPropertyValue(propertyName);
        List<String> fileNames = new ArrayList<>();
        File directory = new File(url);
        if (directory.exists() && directory.isDirectory()) {
            List<File> files = Arrays.stream(directory.listFiles()).toList();
            for (File file : files) {
                int lastDotIndex = file.getName().lastIndexOf(".");
                fileNames.add(file.getName().substring(0, lastDotIndex));
            }
            return fileNames;
        }
        return new ArrayList<>();
    }

}
