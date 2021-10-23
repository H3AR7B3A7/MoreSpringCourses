package be.dog.d.steven.airportmanagement;

import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import be.dog.d.steven.airportmanagement.domain.FlightPrinter;
import be.dog.d.steven.airportmanagement.queries.FlightInformationQueries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@Order(2)
public class ApplicationRunner implements CommandLineRunner {

    private final FlightInformationQueries queries;
    private final MongoTemplate mongoTemplate;

    public ApplicationRunner(FlightInformationQueries queries, MongoTemplate mongoTemplate) {
        this.queries = queries;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
//        queries.createEmptyFlight();

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

        System.out.println("-----\nQUERY: FREE TEXT SEARCH: Rome");
        FlightPrinter.print(queries.findByFreeText("Rome")); // Property: auto-index-creation
        
        markAllFlightsToRomeAsDelayed();
        removeFlightsWithDurationLessThanTwoHours();
    }

    private void removeFlightsWithDurationLessThanTwoHours() {
        Query lessThanTwoHours = Query.query(
                Criteria.where("duration").lt(120)
        );
        mongoTemplate.findAllAndRemove(lessThanTwoHours, FlightInformation.class);
    }

    private void markAllFlightsToRomeAsDelayed() {
        Query departingFromRome = Query.query(
                Criteria.where("destination").is("Rome")
        );
        Update setDelayed = Update.update("isDelayed", true);
        mongoTemplate.updateMulti(
                departingFromRome,
                setDelayed,
                FlightInformation.class
        );
    }
}