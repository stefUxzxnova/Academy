package com.inventory.system.services;

import com.inventory.system.entities.items.InventoryItem;
import com.inventory.system.repositories.InventoryRepo;

import java.util.List;

public class InventoryManager {
    private InventoryRepo inventoryRepo;

    public InventoryManager() {
        this.inventoryRepo = new InventoryRepo("defaultInventory");
    }

    public InventoryManager(String inventoryName){
        this.inventoryRepo = new InventoryRepo(inventoryName);
    }
    public List<InventoryItem> getAllItems() {
        return inventoryRepo.getAll();
    }

    public boolean addItem(InventoryItem item) {
        if (item == null) {
            return false;
        }
        // Validate the item data, e.g., check if required fields are provided
//        if (isValidItem(item)) {
//            // Forward the validated data to the repository for addition
//            return inventoryRepo.create(item);
//        }
        // Handle validation failure, e.g., throw an exception or return null
        return true;
    }


    public boolean updateItem(InventoryItem modifiedItem) {
        return inventoryRepo.update(modifiedItem);
    }

    public boolean deleteItem(long itemId) {
        return inventoryRepo.delete(itemId);
    }

    public String getCurrentInventoryName(){
        int lastDotIndex = inventoryRepo.getCurrentInventoryName().lastIndexOf(".");

        if (lastDotIndex != -1) {
            // Extract the file name without the extension
            return inventoryRepo.getCurrentInventoryName().substring(0, lastDotIndex);
        } else {
            System.out.println("No file extension found.");
        }
        return null;
    }

}
