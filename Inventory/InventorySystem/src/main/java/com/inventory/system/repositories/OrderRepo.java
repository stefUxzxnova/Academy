package com.inventory.system.repositories;

import com.inventory.system.repositories.interfaces.Repository;
import com.inventory.system.utils.FileCreator;

import java.io.File;
import java.util.List;

public class OrderRepo implements Repository{
    private static File ordersFile;

    public OrderRepo(String inventoryName) {
        this.ordersFile = FileCreator.createFile("orders");;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getById(long id) {
        return null;
    }

    @Override
    public Object create(Object entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean update(Object entity) {
        return false;
    }
}
