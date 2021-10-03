package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightCRUDRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class DerivedQueryTest {

    private final Clock millisecondClock = Clock.tick(Clock.systemDefaultZone(), Duration.ofNanos(1_000_000));

    @Autowired
    private FlightCRUDRepository repository;
    
    @BeforeEach
    void setup() {
        repository.deleteAll();
    }
    
    @Test
    void shouldFindFlightsFromLondon() {
        final Flight flight1 = createFlight("London");
        final Flight flight2 = createFlight("London");
        final Flight flight3 = createFlight("New York");
        
        repository.save(flight1);
        repository.save(flight2);
        repository.save(flight3);
        
        List<Flight> flightsToLondon = repository.findByOrigin("London");
        
        assertThat(flightsToLondon).hasSize(2);
        assertThat(flightsToLondon.get(0)).usingRecursiveComparison().isEqualTo(flight1);
        assertThat(flightsToLondon.get(1)).usingRecursiveComparison().isEqualTo(flight2);
    }
    
    @Test
    void shouldFindFlightsFromLondonToParris() {
        final Flight flight1 = createFlight("London", "Paris");
        final Flight flight2 = createFlight("London", "Paris");
        final Flight flight3 = createFlight("Madrid", "Paris");

        repository.save(flight1);
        repository.save(flight2);
        repository.save(flight3);

        List<Flight> flightsFromLondonToParis = repository.findByOriginAndDestination("London", "Paris");

        assertThat(flightsFromLondonToParis).hasSize(2);
        assertThat(flightsFromLondonToParis.get(0)).usingRecursiveComparison().isEqualTo(flight1);
        assertThat(flightsFromLondonToParis.get(1)).usingRecursiveComparison().isEqualTo(flight2);
    }

    @Test
    void shouldFindFlightsFromLondonIgnoringCase() {
        final Flight flight1 = createFlight("LONDON");
        final Flight flight2 = createFlight("London");
        final Flight flight3 = createFlight("Madrid");

        repository.save(flight1);
        repository.save(flight2);
        repository.save(flight3);

        List<Flight> flightsFromLondonToParis = repository.findByOriginIgnoringCase("London");

        assertThat(flightsFromLondonToParis).hasSize(2);
        assertThat(flightsFromLondonToParis.get(0)).usingRecursiveComparison().isEqualTo(flight1);
        assertThat(flightsFromLondonToParis.get(1)).usingRecursiveComparison().isEqualTo(flight2);
    }
    
    private Flight createFlight(String origin, String destination) {
        final Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setScheduledAt(LocalDateTime.now(millisecondClock));
        return flight;
    }
    
    private Flight createFlight(String origin) {
        return createFlight(origin, "Madrid");
    }
}