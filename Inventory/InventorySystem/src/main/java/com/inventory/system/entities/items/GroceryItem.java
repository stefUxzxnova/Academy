package com.inventory.system.entities.items;

import com.inventory.system.entities.items.interfaces.Breakable;
import com.inventory.system.entities.items.interfaces.Perishable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class GroceryItem extends InventoryItem implements Perishable, Breakable {
    @JsonProperty
    private boolean hasExpired;
    @JsonProperty
    private boolean isBroken;

    @JsonProperty
    private LocalDate expirationDate;

    public GroceryItem(String name, String description, int quantity, double price, List<String> category, LocalDate expirationDate) {
        super(name, description, quantity, price, category);
        this.expirationDate = expirationDate;
        this.hasExpired = true;
        this.isBroken = false;
    }

    public GroceryItem(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    public GroceryItem(){}

    @Override
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isHasExpired() {
        return hasExpired;
    }

    public boolean getIsBroken() {
        return isBroken;
    }


    @Override
    public void perish() {
        LocalDate today = LocalDate.now();
        // Calculate the remaining days until the expiration date
        long daysUntilExpiration = ChronoUnit.DAYS.between(today, expirationDate);

        if (daysUntilExpiration >= 0) {
            hasExpired = true;
            this.setPrice(getPrice() * 0.1);
        }
    }

    @Override
    public void displayItemDetails() {
        super.displayItemDetails();
        System.out.print(" EXPIRATION DATE: " + this.expirationDate);
        System.out.print(" hasExpired: " + this.hasExpired);
        System.out.print(" isBroken: " + this.isBroken);
    }

    @Override
    public void breakItem() {
        if (!isBroken) {
            // Electronic item is not broken, so mark it as broken and decrease the price.
            isBroken = true;

            // Decrease the price by a certain percentage (e.g., 10% discount).
            this.setPrice(getPrice() * 0.3 ); // Reduce the price by 10%
        }
    }

    @Override
    public double calculateValue(int quantity) {
        LocalDate today = LocalDate.now();
        // Calculate the remaining days until the expiration date
        long daysUntilExpiration = ChronoUnit.DAYS.between(today, expirationDate);

        if (daysUntilExpiration >= 0) {
            if (daysUntilExpiration <= 7) {
                return (getPrice() * daysUntilExpiration / 7) * quantity;
            } else {
                return getPrice() * quantity;
            }
        } else {
            // The item has already expired, so its value is 0.
            return 0.0;
        }
    }
}
