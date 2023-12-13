package com.example.ProjectFlights.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination; //v2 becomes airport
    //private String departure;
    private LocalDateTime departureTime;
    //TODO seats - typeEnum(Economy, Business, ...)
    //todo arrivalTime
    //todo big Drama Ahead - flightsPath - multiple flights to destination

}
