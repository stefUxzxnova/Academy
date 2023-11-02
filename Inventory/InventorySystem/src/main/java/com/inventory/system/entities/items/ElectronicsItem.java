package com.inventory.system.entities.items;

import com.inventory.system.entities.items.interfaces.Breakable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ElectronicsItem extends InventoryItem implements Breakable{
    @JsonProperty
    private boolean isBroken = false;

    public ElectronicsItem() {
    }

    public ElectronicsItem( String name, String description, int quantity, double price, List<String> category) {
        super(name, description, quantity, price, category);
    }

    public boolean getIsBroken() {
        return isBroken;
    }

    @Override
    public void breakItem() {
        if (!isBroken) {
            isBroken = true;
            this.setPrice(getPrice() * 0.5); // Reduce the price
        }
    }

    public double calculateValue(int quantity) {
        double totalValue = getPrice() * quantity;

        if (totalValue <= 1000) {
            // Apply a base price for items with a totalWeight = 10 or less
            return totalValue;
        } else if (totalValue <= 5000) {
            // Apply a different price for items with a total weight between 10 and 100
            return totalValue - totalValue * 0.1; // 10% discount
        } else {
            // Apply a different price for items with a total weight over 100
            return totalValue - totalValue * 0.2; // 20% discount
        }
    }

    @Override
    public void displayItemDetails() {
        super.displayItemDetails();
        System.out.print(" isBroken: " + this.isBroken);
    }
}
