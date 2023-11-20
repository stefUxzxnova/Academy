package com.staffmanagement.system.utils.interfaces;

import java.util.List;

public interface Reader {
    public <T> List<T> readFile();
    public <T> List<T> readFile(Class<T> valueType);

}
