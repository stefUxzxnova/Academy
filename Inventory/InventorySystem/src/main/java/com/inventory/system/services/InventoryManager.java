package com.inventory.system.services;

import com.inventory.system.entities.enums.ProductCategory;
import com.inventory.system.entities.items.InventoryItem;
import com.inventory.system.repositories.InventoryRepo;
import com.inventory.system.services.enums.SortCriteria;

import java.util.Collections;
import java.util.Comparator;
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
    public InventoryItem getById(long id) {
        return inventoryRepo.getById(id);
    }


    public boolean addItem(InventoryItem item) {
        if (item == null) {
            return false;
        }
        InventoryItem createdItem = inventoryRepo.create(item);
        if (createdItem != null) {
            return true;
        }
        return false;
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

    public List<InventoryItem> getItemsSortedBy(SortCriteria sortBy){
//        List<InventoryItem> list = inventoryRepo.getAll().stream().sorted(
//                (a, b) -> {
//                    if (a.getPrice() > b.getPrice()) {
//                        return 1;
//                    } else if (a.getPrice() < b.getPrice()) {
//                        return -1;
//                    }else{
//                        return 0;
//                    }
//                }).toList();

        Comparator<InventoryItem> comparator = null;
        switch (sortBy){
            case NAME -> comparator = Comparator.comparing(InventoryItem::getName);
            case PRICE -> comparator = Comparator.comparing(InventoryItem::getPrice);
        }
        List<InventoryItem> inventoryItems = inventoryRepo.getAll();
        Collections.sort(inventoryRepo.getAll(), comparator);
        return inventoryItems;
    }

    public List<InventoryItem> getItemsByCategory(ProductCategory category){
        List<InventoryItem> list = inventoryRepo.getAll()
                .stream()
                .filter(e -> e.getCategory().
                        contains(category.getDisplayName()))
                .toList();
        return list;
    }

}
