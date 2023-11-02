package com.inventory.system.repositories;

import com.inventory.system.entities.items.InventoryItem;
import com.inventory.system.repositories.interfaces.Repository;
import com.inventory.system.utils.FileCreator;
import com.inventory.system.utils.FileReader;
import com.inventory.system.utils.FileWriter;

import java.io.File;
import java.util.List;

public class InventoryRepo implements Repository {
    private static File inventoryFile;

    public InventoryRepo(String inventoryName) {
        inventoryFile = FileCreator.createFile("inventories/" + inventoryName + ".json");
    }

    @Override
    public List<InventoryItem> getAll() {
        return FileReader.readFile(inventoryFile, InventoryItem.class);
    }

    @Override
    public InventoryItem getById(long id) {
        List<InventoryItem> list = FileReader.readFile(inventoryFile, InventoryItem.class);
        return list.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public InventoryItem create(Object entity) {
        List<InventoryItem> list = FileReader.readFile(inventoryFile, InventoryItem.class);
        list.add((InventoryItem) entity);
        if (FileWriter.writeJsonFile(inventoryFile, list)) {
            return (InventoryItem) entity;
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        List<InventoryItem> list = FileReader.readFile(inventoryFile, InventoryItem.class);
        boolean removed = list.removeIf(item -> item.getId() == id);
        if (removed) {
            return FileWriter.writeJsonFile(inventoryFile, list);
        }
        return false;
    }

    @Override
    public boolean update(Object entity) {
        List<InventoryItem> list = FileReader.readFile(inventoryFile, InventoryItem.class);
        InventoryItem modifiedItem = (InventoryItem) entity;
        for (InventoryItem item : list) {
            if (item.getId() == modifiedItem.getId()) {
                int index = list.indexOf(item);
                list.set(index, modifiedItem);
            }
        }
        return FileWriter.writeJsonFile(inventoryFile, list);
    }

    public String getCurrentInventoryName(){
        return inventoryFile.getName();
    }
}
