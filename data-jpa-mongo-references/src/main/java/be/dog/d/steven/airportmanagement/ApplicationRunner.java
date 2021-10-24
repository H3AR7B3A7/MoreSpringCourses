package be.dog.d.steven.airportmanagement;

import be.dog.d.steven.airportmanagement.domain.Airport;
import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import be.dog.d.steven.airportmanagement.domain.FlightPrinter;
import be.dog.d.steven.airportmanagement.repository.AirportRepository;
import be.dog.d.steven.airportmanagement.repository.FlightInformationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SameParameterValue")
@Service
@Order(2)
public class ApplicationRunner implements CommandLineRunner {
    private final FlightInformationRepository flightRepository;
    private final AirportRepository airportRepository;

    public ApplicationRunner(FlightInformationRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public void run(String... args) {
        // Single update point
        Airport rome = airportRepository.findById("1d1aab22-670b-48cb-a027-721e2055731f").get();
        rome.setName("Leonardo da Vinci (Fiumicino)");
        airportRepository.save(rome);

        System.out.println("-> AFTER UPDATE TO ROME AIRPORT");
        List<FlightInformation> flights = flightRepository.findAll();
        FlightPrinter.print(flights);
    }
}