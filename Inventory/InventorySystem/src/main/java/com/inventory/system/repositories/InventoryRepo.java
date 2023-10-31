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
            sreturn FileWriter.writeJsonFile(inventoryFile, list);
        }
        return false;
    }

    @Override
    public boolean update(Object entity) {
        List<InventoryItem> list = FileReader.readFile(inventoryFile, InventoryItem.class);
        int index = list.indexOf(entity);
        if (index != -1) {
            list.set(index, (InventoryItem) entity);
            /*
             * update the json file with the updated list */
            return FileWriter.writeJsonFile(inventoryFile, list);
        } else {
            // if item with the given id is not found
            return false;
        }
    }

    public String getCurrentInventoryName(){
        return inventoryFile.getName();
    }
}
