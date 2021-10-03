package com.pluralsight.springdataoverview.service;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FlightService {

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public void saveFlight(Flight flight) {
        repository.save(flight);
        throw new RuntimeException("I failed");
    }
    
    @Transactional
    public void saveFlightTransactional(Flight flight) {
        repository.save(flight);
        throw new RuntimeException("I failed");
    }
}