package com.inventory.system.entities.enums;

public enum OrderStatus{
    Pending("Pending"),
    Done("Done");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
