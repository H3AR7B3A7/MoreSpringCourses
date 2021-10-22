package be.dog.d.steven.airportmanagement;

import be.dog.d.steven.airportmanagement.domain.FlightPrinter;
import be.dog.d.steven.airportmanagement.queries.FlightInformationQueries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final FlightInformationQueries queries;

    public ApplicationRunner(FlightInformationQueries queries) {
        this.queries = queries;
    }

    @Override
    public void run(String... args) throws Exception {
//        queries.createOne();

        System.out.println("------\nQUERY: ALL FLIGHTS ORDERED BY DEPARTURE");
        FlightPrinter.print(queries.findAllFlights("departure", 0, 3));

        System.out.println("------\nQUERY: DEPART AT NEW YORK");
        FlightPrinter.print(queries.findByDeparture("New York"));

        System.out.println("------\nQUERY: DELAY DEPARTURE AT GIVEN MADRID");
        FlightPrinter.print(queries.findDelayedAtDeparture("Madrid"));

        System.out.println("------\nQUERY: DURATION BETWEEN 60 AND 120 MINUTES");
        FlightPrinter.print(queries.findByDurationBetween(60, 120));

        System.out.println("------\nQUERY: USING A 737 AIRCRAFT");
        FlightPrinter.print(queries.findByAircraft("737"));

//        System.out.println("-----\nQUERY: FREE TEXT SEARCH: Rome");
//        FlightPrinter.print(queries.findByFreeText("Rome")); // WTF IS WRONG WITH THIS????
    }
}