package com.example.ProjectFlights.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne //@ManyToMany //when travelling with multiple flights
    private Flight flight;

    private TicketStatus status;

    private double price;
    private String seat;

}
