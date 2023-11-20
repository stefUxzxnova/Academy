package com.staffmanagement.system.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.staffmanagement.system.utils.interfaces.Reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader implements Reader {
    private final File file;
    public JsonReader(File file) {
        this.file = file;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public <T> List<T> readFile(Class<T> valueType){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try{
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, objectMapper
                    .getTypeFactory()
                    .constructCollectionType(List.class, valueType));

        } catch (IOException e){
            System.out.println("Error occurred during the backup.");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public <T> List<T> readFile() {
        return null;
    }
}
