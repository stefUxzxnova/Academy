package com.ecommerce.system.repositories.interfaces;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();
    T getById(long id);
    T create(T entity);
}
