package com.booking.system.enums;

public enum UserRoles {
    ADMIN("Admin"),
    REGISTERED("Registered");

    private final String displayName;

    UserRoles(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;

    }
}
