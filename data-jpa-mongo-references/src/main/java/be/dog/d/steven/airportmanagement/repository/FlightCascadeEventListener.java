package be.dog.d.steven.airportmanagement.repository;

import be.dog.d.steven.airportmanagement.domain.Airport;
import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

//@Component
public class FlightCascadeEventListener extends AbstractMongoEventListener<Object> {
    private final MongoTemplate mongoTemplate;

    public FlightCascadeEventListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object doc = event.getSource();
        
        if ((doc instanceof FlightInformation) && (((FlightInformation) doc).getDeparture() != null)){
            Airport departure = ((FlightInformation) doc).getDeparture();
            mongoTemplate.save(departure);
        }

        if ((doc instanceof FlightInformation) && (((FlightInformation) doc).getDestination() != null)){
            Airport destination = ((FlightInformation) doc).getDestination();
            mongoTemplate.save(destination);
        }
        
    }
}