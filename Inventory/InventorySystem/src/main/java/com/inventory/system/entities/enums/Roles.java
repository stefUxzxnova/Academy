package com.inventory.system.entities.enums;

public enum Roles {
    ADMIN("Admin"),
    REGULARUSER("Regular");

    private final String displayName;

    Roles(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
