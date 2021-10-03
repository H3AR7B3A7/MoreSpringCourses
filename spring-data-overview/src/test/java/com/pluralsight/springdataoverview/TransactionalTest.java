package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import com.pluralsight.springdataoverview.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransactionalTest {

    @Autowired
    private FlightRepository repository;
    
    @Autowired
    private FlightService service;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }
    
    @Test
    void shouldNotRollBackWhenThereIsNoTransaction() {
        try {
            service.saveFlight(new Flight());
        } catch (Exception e) {
            // Do nothing
        } finally {
            assertThat(repository.findAll()).isNotEmpty();
        }
    }

    @Test
    void shouldRollBackWhenThereIsATransaction() {
        try {
            service.saveFlightTransactional(new Flight());
        } catch (Exception e) {
            // Do nothing
        } finally {
            assertThat(repository.findAll()).isEmpty();
        }
    }
}