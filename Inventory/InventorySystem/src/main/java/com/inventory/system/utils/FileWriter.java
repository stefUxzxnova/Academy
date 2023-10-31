package com.inventory.system.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        //allowing to include type information in serialized JSON (for polymorphic types)
        MAPPER.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        //javaTimeModule to serialize the dates fields
        MAPPER.registerModule(new JavaTimeModule());
    }

    public static <T> boolean writeJsonFile(File file, List<T> items){

        try {
            //convert object to json format
            MAPPER.writeValue(file, items);
        }
        catch (IOException e) {
            System.out.println("Data has not been written to the file.");
            return false;
        }
        return true;
    }
}
