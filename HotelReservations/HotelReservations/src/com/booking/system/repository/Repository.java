package com.booking.system.repository;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();
    T getById(int id);
    boolean create(T entity);
    boolean delete(int id);
    void update(T entity);

}
