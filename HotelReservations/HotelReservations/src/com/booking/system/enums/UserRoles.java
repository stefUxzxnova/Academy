package com.booking.system.enums;

public enum UserRoles {
    ADMIN("Admin"),
    REGISTEREDUSER("Registered user");

    private final String displayName;

    UserRoles(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;

    }

}
