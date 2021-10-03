package com.pluralsight.springdataoverview.repository;

import com.pluralsight.springdataoverview.entity.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightCRUDRepository extends CrudRepository<Flight, Long> {
    
    List<Flight> findByOrigin(String origin);
    List<Flight> findByOriginAndDestination(String origin, String destination);
    List<Flight> findByOriginIgnoringCase(String origin);
}