package com.inventory.system.entities.items;

import com.fasterxml.jackson.annotation.*;

import java.util.List;


@JsonSubTypes({
        @JsonSubTypes.Type(value= GroceryItem.class, name = "GroceryItem"),
        @JsonSubTypes.Type(value= ElectronicsItem.class, name = "ElectronicsItem"),
        @JsonSubTypes.Type(value= FragileItem.class, name = "FragileItem")
})

public class InventoryItem extends AbstractItem {
    public static long uid = 1;
    @JsonProperty
    private long id;
    @JsonProperty
    private int quantity;
    @JsonProperty
    private double price;

    public InventoryItem(String name, String description, int quantity, double price, List<String> category) {
        super(name, description, category);
        this.id = uid++;
        this.quantity = quantity;
        this.price = price;
    }

    public InventoryItem() {
    }

    @Override
    public double calculateValue() {
        return this.price * this.quantity;
    }

    public double calculateValue(int quantity) {
        return this.price * quantity;
    }
    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean sell(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity for selling.");
            return false;
        }

        if (quantity > this.quantity) {
            System.out.println("Not enough items in stock to sell.");
            return false;
        }

        // Reducing the quantity and updating sales records.
        this.quantity -= quantity;
        //double revenue = price * quantity;
        //System.out.println(quantity + " item(s) sold for a total of $" + revenue);
        return true;
    }

    @Override
    public void displayItemDetails() {
        System.out.print("Id: " + this.id);
        super.displayItemDetails();
        System.out.print(", Quantity: " + this.quantity);
        System.out.print(", Total Value: $" + calculateValue());
    }

    @Override
    public String toString() {
        return "InventoryItem{ id=" + id + ", name= " + getName() + ", quantity=" + quantity + ", price=" + price + '}';
    }
}
