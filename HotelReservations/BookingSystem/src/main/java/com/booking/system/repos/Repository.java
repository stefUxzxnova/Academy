package com.booking.system.repos;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();
    T getById(int id);
    boolean create(T entity);
    boolean delete(int id);
    boolean update(T entity);
}
