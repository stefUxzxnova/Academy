package com.inventory.system.repositories;

import com.inventory.system.entities.order.Order;
import com.inventory.system.entities.user.User;
import com.inventory.system.repositories.interfaces.Repository;
import com.inventory.system.utils.FileCreator;
import com.inventory.system.utils.FileReader;
import com.inventory.system.utils.FileWriter;

import java.io.File;
import java.util.List;

public class OrderRepo implements Repository{
    private static File ordersFile;

    public OrderRepo(String inventoryName) {
        this.ordersFile = FileCreator.createFile("orders/" + inventoryName + ".json");;
    }

    @Override
    public List<Order> getAll() {
        return FileReader.readFile(ordersFile, Order.class);
    }

    @Override
    public Order getById(long id) {
        List<Order> orders = FileReader.readFile(ordersFile, Order.class);
        return orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Order create(Object entity) {
        List<Order> orders = FileReader.readFile(ordersFile, Order.class);
        orders.add((Order) entity);
        if (FileWriter.writeJsonFile(ordersFile, orders)) {
            return (Order) entity;
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        List<Order> orders = FileReader.readFile(ordersFile, Order.class);
        Order order = orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
        if (order != null) {
            orders.remove(order);
            return FileWriter.writeJsonFile(ordersFile, orders);
        }
        return false;
    }

    @Override
    public boolean update(Object entity) {
        List<Order> list = FileReader.readFile(ordersFile, Order.class);
        int index = list.indexOf(entity);
        if (index != -1) {
            list.set(index, (Order) entity);
            /*
             * update the json file with the updated list */
            return FileWriter.writeJsonFile(ordersFile, list);
        } else {
            // if user with the given id is not found
            return false;
        }
    }
}
