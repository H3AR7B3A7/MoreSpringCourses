package be.dog.d.steven.airportmanagement;

import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    public ApplicationRunner(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        FlightInformation emptyFlight = new FlightInformation();
        this.mongoTemplate.save(emptyFlight);

        System.out.println("Application started...");
    }
}