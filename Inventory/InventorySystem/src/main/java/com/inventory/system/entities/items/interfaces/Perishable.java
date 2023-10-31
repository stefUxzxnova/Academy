package com.inventory.system.entities.items.interfaces;

import java.time.LocalDate;

public interface Perishable {
    LocalDate getExpirationDate();
    void perish();
}
