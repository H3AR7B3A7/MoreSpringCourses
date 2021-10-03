package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomImplTest {

    @Autowired
    private FlightRepository repository;
    
    @Test
    void shouldSaveCustomImpl() {
        final Flight toDelete = createFlight("London");
        final Flight toKeep = createFlight("Madrid");
        
        repository.save(toDelete);
        repository.save(toKeep);
        
        repository.deleteByOrigin("London");
        
        assertThat(repository.findAll())
                .hasSize(1)
                .first()
                .usingRecursiveComparison()
                .isEqualTo(toKeep);
    }
    
    private Flight createFlight(String origin) {
        final Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination("Tokyo");
        flight.setScheduledAt(LocalDateTime.now());
        return flight;
    }
}