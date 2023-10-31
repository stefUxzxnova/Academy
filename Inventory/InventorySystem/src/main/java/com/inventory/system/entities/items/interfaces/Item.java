package com.inventory.system.entities.items.interfaces;

public interface Item {
    String getName();
    String getDescription();
    // Method to calculate the total value of the item (quantity * price).
    double calculateValue();
    void displayItemDetails();

}
