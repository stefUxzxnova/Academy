package com.staffmanagement.system.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.staffmanagement.system.utils.interfaces.Writer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWriter implements Writer {
    private final File file;

    public JsonWriter(File file) {
        this.file = file;
    }

    @Override
    public <T> boolean writeFile(List<T> items){
        ObjectMapper objectMapper = new ObjectMapper();
        //javaTimeModule to serialize/deserialize the dates fields
        objectMapper.registerModule(new JavaTimeModule());
        try {
            //convert object to json format
            objectMapper.writeValue(file, items);
        }
        catch (IOException e) {
            System.out.println("Data has not been written to the file.");
            return false;
        }
        return true;
    }
}
