package com.booking.system.models;

public abstract class BaseEntity {
    private static int nextId = 1;
    private int id;

    protected BaseEntity(){
        id = nextId++;
    }
    public int getId() {
        return id;
    }
}
