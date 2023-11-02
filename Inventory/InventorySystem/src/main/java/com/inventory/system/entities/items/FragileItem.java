package com.inventory.system.entities.items;

import com.inventory.system.entities.items.interfaces.Breakable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FragileItem extends InventoryItem implements Breakable {
    @JsonProperty
    private boolean isBroken = false;
    @JsonProperty
    private double weight;

    public FragileItem(String name, String description, int quantity, double price, List<String> category, double weight) {
        super(name, description, quantity, price, category);
        this.weight = weight;
    }

    public FragileItem() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void breakItem() {
        if (!isBroken) {
            // Electronic item is not broken, so mark it as broken and decrease the price.
            isBroken = true;

            // Decrease the price by a certain percentage (e.g., 10% discount).
            this.setPrice(getWeight() * 0.1);// Reduce the price by 10%
        }
    }

    public double calculateValue(int quantity) {
        double totalWeight = weight * quantity;
        double totalPrice = getPrice() * quantity;

        if (totalWeight <= 10) {
            // Apply a base price for items with a totalWeight = 10 or less
            return totalPrice;
        } else if (totalWeight <= 100) {
            // Apply a different price for items with a total weight between 10 and 100
            return totalPrice * 1.1; // 10% increase
        } else {
            // Apply a different price for items with a total weight over 100
            return totalPrice * 1.2; // 20% increase
        }
    }

    @Override
    public void displayItemDetails() {
        super.displayItemDetails();
        System.out.print(", IsBroken: " + this.isBroken);
        System.out.print(", Weight: " + this.weight);
    }
}
