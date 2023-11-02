package com.inventory.system.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        //allowing to include type information in serialized JSON (for polymorphic types)
        MAPPER.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        MAPPER.registerModule(new JavaTimeModule());
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static <T> List<T> readFile(File file, Class<T> valueType){
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        try {
            return MAPPER.readValue(file, MAPPER.getTypeFactory().constructCollectionType(List.class, valueType));
        } catch (IOException e) {
            System.out.println("Error occurred during reading the file.");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
