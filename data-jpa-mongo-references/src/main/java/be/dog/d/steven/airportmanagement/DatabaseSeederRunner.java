package be.dog.d.steven.airportmanagement;

import be.dog.d.steven.airportmanagement.domain.Aircraft;
import be.dog.d.steven.airportmanagement.domain.Airport;
import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import be.dog.d.steven.airportmanagement.domain.FlightPrinter;
import be.dog.d.steven.airportmanagement.domain.FlightType;
import be.dog.d.steven.airportmanagement.repository.AirportRepository;
import be.dog.d.steven.airportmanagement.repository.FlightInformationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
@ConditionalOnProperty(
        prefix = "airport",
        value = "seeding.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class DatabaseSeederRunner implements CommandLineRunner {
    private final FlightInformationRepository flightRepository;
    private final AirportRepository airportRepository;
    private final MongoTemplate mongoTemplate;

    public DatabaseSeederRunner(FlightInformationRepository flightRepository,
                                AirportRepository airportRepository,
                                MongoTemplate mongoTemplate) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) {
        empty();
        seed();
    }

    private void seed() {
        // Airports
        Airport rome = new Airport(
                "1d1aab22-670b-48cb-a027-721e2055731f",
                "Leonardo da Vinci",
                "Rome",
                42995119);
        Airport paris = new Airport(
                "d04a8c26-7527-407c-81ef-680e5de34cea",
                "Charles de Gaulle",
                "Paris",
                72229723);
        Airport copenhagen = new Airport(
                "7ed990d2-6471-485d-931e-c77729dc0f25",
                "Copenhagen Airport",
                "Copenhagen",
                30298531);

        // Flight data
        FlightInformation flightOne = new FlightInformation();
        flightOne.setId("b8b50563-ca90-4afc-9d43-b674892a525c");
        flightOne.setDelayed(false);
        flightOne.setDeparture(rome);
        flightOne.setDestination(paris);
        flightOne.setDepartureDate(LocalDate.of(2019, 3, 12));
        flightOne.setType(FlightType.International);
        flightOne.setDurationMin(80);
        flightOne.setAircraft(new Aircraft("737", 180));

        FlightInformation flightTwo = new FlightInformation();
        flightTwo.setId("c0725fbb-eebb-4aae-8b41-3887793d0c50");
        flightTwo.setDelayed(false);
        flightTwo.setDeparture(paris);
        flightTwo.setDestination(copenhagen);
        flightTwo.setDepartureDate(LocalDate.of(2019, 5, 11));
        flightTwo.setType(FlightType.International);
        flightTwo.setDurationMin(600);
        flightTwo.setAircraft(new Aircraft("747", 300));

        // Seed - Replaced by FlightCascadeEventListener
//        List<Airport> airports = Arrays.asList(
//                copenhagen,
//                paris,
//                rome
//        );

        List<FlightInformation> flights = Arrays.asList(
                flightOne,
                flightTwo
        );
        
//        airportRepository.insert(airports);
        flightRepository.insert(flights);

        // Print
        FlightPrinter.print(flights);
    }


    private void empty() {
        this.mongoTemplate.dropCollection(Airport.class);
        this.mongoTemplate.dropCollection(FlightInformation.class);
    }
}