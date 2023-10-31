package com.inventory.system.entities.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inventory.system.entities.enums.OrderStatus;
import com.inventory.system.entities.items.InventoryItem;

import java.time.LocalDate;
import java.util.List;

public class Order {
    public static long uid = 1;
    @JsonProperty
    private long id;
    @JsonProperty
    private long userId;

    @JsonProperty
    private String inventoryName;
    @JsonProperty
    private List<InventoryItem> orderItems;
    @JsonProperty
    private double totalPrice;
    @JsonProperty
    private LocalDate madeOn;
    @JsonProperty
    private String status;

    public Order(long userId, List<InventoryItem> orderItems, LocalDate madeOn) {
        this.id = uid++;
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalPrice = calculateTotalPrice();
        this.madeOn = madeOn;
        this.status = OrderStatus.Pending.getDisplayName();
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<InventoryItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<InventoryItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;
        for (InventoryItem item : getOrderItems()) {
            totalPrice += item.calculateValue();
        }
        return totalPrice;
    }

    public LocalDate getMadeOn() {
        return madeOn;
    }

    public void setMadeOn(LocalDate madeOn) {
        this.madeOn = madeOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderItems=" + orderItems +
                ", totalPrice=" + totalPrice +
                ", madeOn=" + madeOn +
                '}';
    }
}
