package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.Flight;
import com.pluralsight.springdataoverview.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.DESC;

@DataMongoTest
public class PagingAndSortingTest {

    private final Clock millisecondClock = Clock.tick(Clock.systemDefaultZone(), Duration.ofNanos(1_000_000));

    @Autowired
    private FlightRepository repository;
    
    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void shouldSortFlightsByDestination() {
        final Flight madrid = createFlight("Madrid");
        final Flight london = createFlight("London");
        final Flight paris = createFlight("Paris");

        repository.save(madrid);
        repository.save(london);
        repository.save(paris);

        final Iterable<Flight> flights = repository.findAll(Sort.by("destination"));

        assertThat(flights).hasSize(3);
    
        final Iterator<Flight> iterator = flights.iterator();
        
        assertThat(iterator.next().getDestination()).isEqualTo("London");
        assertThat(iterator.next().getDestination()).isEqualTo("Madrid");
        assertThat(iterator.next().getDestination()).isEqualTo("Paris");
    }

    @Test
    void shouldSortFlightsByScheduleAndThenName() {
        final LocalDateTime now = LocalDateTime.now(millisecondClock);
        final Flight flight1 = createFlight("Paris", now);
        final Flight flight2 = createFlight("Paris", now.plusHours(2));
        final Flight flight3 = createFlight("Paris", now.plusHours(1));
        final Flight flight4 = createFlight("Madrid", now.plusHours(1));

        repository.save(flight1);
        repository.save(flight2);
        repository.save(flight3);
        repository.save(flight4);

        final Iterable<Flight> flights = repository.findAll(Sort.by("scheduledAt", "destination"));

        assertThat(flights).hasSize(4);

        final Iterator<Flight> iterator = flights.iterator();

        assertThat(iterator.next()).usingRecursiveComparison().isEqualTo(flight1);
        assertThat(iterator.next()).usingRecursiveComparison().isEqualTo(flight4);
        assertThat(iterator.next()).usingRecursiveComparison().isEqualTo(flight3);
        assertThat(iterator.next()).usingRecursiveComparison().isEqualTo(flight2);
    }
    
    @Test
    void shouldPageResults() {
        for (int i = 0; i < 50; i++) {
            repository.save(createFlight(String.valueOf(i)));
        }
        
        final Page<Flight> page = repository.findAll(PageRequest.of(2, 5));
        
        assertThat(page.getTotalElements()).isEqualTo(50);
        assertThat(page.getNumberOfElements()).isEqualTo(5);
        assertThat(page.getTotalPages()).isEqualTo(10);
        assertThat(page.getContent())
                .extracting(Flight::getDestination)
                .containsExactly("10", "11", "12", "13", "14");
    }

    @Test
    void shouldPageAndSortResults() {
        for (int i = 0; i < 50; i++) {
            repository.save(createFlight(String.valueOf(i)));
        }

        final Page<Flight> page = repository
                .findAll(PageRequest.of(2, 5, Sort.by(DESC, "destination")));

        assertThat(page.getTotalElements()).isEqualTo(50);
        assertThat(page.getNumberOfElements()).isEqualTo(5);
        assertThat(page.getTotalPages()).isEqualTo(10);
        assertThat(page.getContent())
                .extracting(Flight::getDestination)
                .containsExactly("44", "43", "42", "41", "40");
    }

    @Test
    void shouldPageAndSortADerivedQuery() {
        for (int i = 0; i < 10; i++) {
            Flight flight = createFlight(String.valueOf(i));
            flight.setOrigin("Paris");
            repository.save(flight);
        }

        for (int i = 0; i < 10; i++) {
            Flight flight = createFlight(String.valueOf(i));
            flight.setOrigin("London");
            repository.save(flight);
        }

        final Page<Flight> page = repository
                .findByOrigin("London", PageRequest.of(0, 5, Sort.by(DESC, "destination")));

        assertThat(page.getTotalElements()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(5);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getContent())
                .extracting(Flight::getDestination)
                .containsExactly("9", "8", "7", "6", "5");
    }

    private Flight createFlight(String destination, LocalDateTime scheduledAt) {
        final Flight flight = new Flight();
        flight.setOrigin("London");
        flight.setDestination(destination);
        flight.setScheduledAt(scheduledAt);
        return flight;
    }
    
    private Flight createFlight(String destination) {
        return createFlight(destination, LocalDateTime.now(millisecondClock));
    }
}