package com.example.ProjectFlights.repository;

import com.example.ProjectFlights.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
