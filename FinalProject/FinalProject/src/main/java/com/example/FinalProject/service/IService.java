package com.example.FinalProject.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T item);
    void delete(Long id);
}
