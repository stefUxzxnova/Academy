package com.inventory.system.services;

import com.inventory.system.repositories.OrderRepo;

public class OrdersManager {
    private OrderRepo orderRepo;

    public OrdersManager() {
        this.orderRepo = new OrderRepo("default");
    }
    public OrdersManager(String inventoryName) {
        this.orderRepo = new OrderRepo(inventoryName);
    }


}
