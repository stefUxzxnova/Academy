package com.example.ProjectFlights.repository;

import com.example.ProjectFlights.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
