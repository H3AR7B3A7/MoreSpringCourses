package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightCRUDRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CrudTests {

    @Autowired
    private FlightCRUDRepository repository;

    @Test
    void shouldPerformCRUDOperations() {
        final Flight flight =  new Flight();
        flight.setOrigin("London");
        flight.setDestination("New York");
        flight.setScheduledAt(LocalDateTime.parse("2011-12-13T12:12:00"));
        
        repository.save(flight);
        
        assertThat(repository.findAll())
                .hasSize(1)
                .first()
                .usingRecursiveComparison()
                .isEqualTo(flight);
        
        repository.deleteById(flight.getId());
        
        assertThat(repository.count()).isZero();
    }
}