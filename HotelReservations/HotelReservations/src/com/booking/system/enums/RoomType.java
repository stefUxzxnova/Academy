package com.booking.system.enums;

public enum RoomType {

        SINGLE("SingleRoom"),
        DOUBLE("Double Room"),
        SUITE("Suite"),
        DELUXE("Deluxe Room");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;

    }
}
