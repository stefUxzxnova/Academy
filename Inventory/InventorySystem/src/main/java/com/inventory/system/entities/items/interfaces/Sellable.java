package com.inventory.system.entities.items.interfaces;

public interface Sellable{
    double getPrice();
    void setPrice(double price);
    boolean sell(int quantity);
}
