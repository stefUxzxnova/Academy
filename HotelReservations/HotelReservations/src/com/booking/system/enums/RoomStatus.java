package com.booking.system.enums;

public enum RoomStatus {
        FREE("Free"),
        BOOKED("Booked"),
        MAINTENANCE("maintenance");

    private final String displayName;

    RoomStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
