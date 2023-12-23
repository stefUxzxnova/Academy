package com.example.FinalProject.utils.fileOperations.fileReader;

import java.util.List;

public interface Reader<T> {
    List<T> read(String fileName);
}
