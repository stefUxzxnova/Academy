package com.staffmanagement.system.utils.interfaces;

import java.util.List;

public interface Writer {
    <T> boolean writeFile(List<T> items);
}
