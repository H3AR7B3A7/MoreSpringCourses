package be.dog.d.steven.airportmanagement;

import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import be.dog.d.steven.airportmanagement.domain.FlightPrinter;
import be.dog.d.steven.airportmanagement.repository.FlightInformationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SameParameterValue")
@Service
@Order(4)
public class ApplicationRunner2 implements CommandLineRunner {
    private final FlightInformationRepository repository;

    public ApplicationRunner2(FlightInformationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        printById("4d23fd8b-47a7-45f8-958c-94d0e21488b2");

        delayFlight("4d23fd8b-47a7-45f8-958c-94d0e21488b2", 30);

        removeFlight("4d23fd8b-47a7-45f8-958c-94d0e21488b2");

        printByDepartureAndDestination("Madrid", "Barcelona");

        printByMinNbSeats(200);
    }

    private void printById(String id) throws IllegalArgumentException {
        System.out.println("Flight " + id);

        Optional<FlightInformation> flight = this.repository
                .findById(id);
        FlightPrinter.print(List.of(flight.orElseThrow(IllegalArgumentException::new)));
    }

    private void delayFlight(String id, int duration) {
        Optional<FlightInformation> optional = this.repository
                .findById(id);
        FlightInformation flight = optional.orElseThrow(IllegalArgumentException::new);
        flight.setDurationMin(flight.getDurationMin() + duration);

        this.repository.save(flight);
        System.out.println("Updated flight " + id + "\n");
    }

    private void removeFlight(String id) {
        this.repository.deleteById(id);
        System.out.println("Deleted flight " + id + "\n");
    }

    private void printByDepartureAndDestination(String dep, String dst) {
        System.out.println("Flights from " + dep + " to " + dst);

        List<FlightInformation> flights = this.repository
                .findByDepartureCityAndDestinationCity(dep, dst);

        FlightPrinter.print(flights);
    }

    private void printByMinNbSeats(int minNbSeats) {
        System.out.println("Flights by min nb seats " + minNbSeats);

        List<FlightInformation> flights = this.repository
                .findByMinAircraftNbSeats(200);

        FlightPrinter.print(flights);
    }
}