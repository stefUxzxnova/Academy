package com.booking.system.entities;

public abstract class BaseEntity {
    private int nextId;
    private int id;

    public BaseEntity(int nextId) {
        this.nextId = nextId;
        this.id = this.nextId++;
    }
    public BaseEntity(){
    }

    public int getId() {
        return id;
    }
}
