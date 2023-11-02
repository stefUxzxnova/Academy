package com.inventory.system.services;

import com.inventory.system.entities.enums.OrderStatus;
import com.inventory.system.entities.items.InventoryItem;
import com.inventory.system.entities.order.Order;
import com.inventory.system.repositories.OrderRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersManager {
    private OrderRepo orderRepo;

    public OrdersManager() {
        this.orderRepo = new OrderRepo("default");
    }
    public OrdersManager(String inventoryName) {
        this.orderRepo = new OrderRepo(inventoryName);
    }

    public List<Order> getAllUsers() {
        return orderRepo.getAll();
    }
    public Order getById(long id) {
        return orderRepo.getById(id);
    }
    public Order getOrdersOfLoggedUser(long loggedUserId){
        List<Order> allOrders = orderRepo.getAll();
        Order order = allOrders.stream()
                .filter(u -> u.getUserId() == loggedUserId)
                .findFirst()
                .orElse(null);
        return order;
    }

    public Order create(long loggedUserId, Map<InventoryItem, Integer> orderItems, String orderStatus, InventoryManager inventoryManager) {
        List<InventoryItem> items = new ArrayList<>();
        for (var entry : orderItems.entrySet()) {
            items.add(entry.getKey());
        }
        Order order = new Order(loggedUserId, items, orderStatus);
        orderRepo.create(order);

        if (orderStatus.equals(OrderStatus.Done.getDisplayName())) {
            changeQuantities(orderItems, inventoryManager);
        }
        return order;
    }

    private void changeQuantities(Map<InventoryItem, Integer> orderItems, InventoryManager inventoryManager) {
        for (var entry : orderItems.entrySet()) {
            InventoryItem item = entry.getKey();
            item.sell(entry.getValue());
            inventoryManager.updateItem(item);
        }
    }

    public double calculateOrderTotalPrice(Map<InventoryItem, Integer> orderItems){
        double totalPrice = 0;
        for (var entry : orderItems.entrySet()) {
            InventoryItem item = entry.getKey();
            totalPrice += item.calculateValue(entry.getValue());
        }
        return totalPrice;
    }


}
