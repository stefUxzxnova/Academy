package com.inventory.system.entities.items;

import com.inventory.system.entities.items.interfaces.Categorizable;
import com.inventory.system.entities.items.interfaces.Item;
import com.inventory.system.entities.items.interfaces.Sellable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public abstract class AbstractItem implements Item, Categorizable, Sellable{
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<String> category;

    public AbstractItem(String name, String description, List<String> category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }
    public AbstractItem(){}

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void displayItemDetails() {
        System.out.print(", Item Name: " + getName());
        System.out.print(", Description: " + getDescription());
        System.out.print(", Price: $" + getPrice());
        System.out.print(", Category: " + getCategory());
    }


    public List<String> getCategory() {
        return this.category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

//    @Override
//    public boolean sell(int quantity) {
//        if (quantity <= 0) {
//            System.out.println("Invalid quantity for selling.");
//            return false;
//        }
//
//        if (quantity > this.quantity) {
//            System.out.println("Not enough items in stock to sell.");
//            return false;
//        }
//
//        // Reducing the quantity and updating sales records.
//        this.quantity -= quantity;
//        //double revenue = price * quantity;
//        //System.out.println(quantity + " item(s) sold for a total of $" + revenue);
//        return true;
//    }
}
