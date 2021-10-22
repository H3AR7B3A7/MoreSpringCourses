package be.dog.d.steven.airportmanagement.queries;

import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import be.dog.d.steven.airportmanagement.domain.FlightType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightInformationQueries {
    private final MongoTemplate mongoTemplate;

    public FlightInformationQueries(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    public List<FlightInformation> findAllFlights(String field, int pageNumber, int pageSize) {
        Query allPagedAndSorted = new Query()
                .with(Sort.by(Sort.Direction.ASC, field))
                .with(PageRequest.of(pageNumber, pageSize));
        
        return mongoTemplate.find(allPagedAndSorted, FlightInformation.class);
    }
    
    public FlightInformation findSingleById(String id) {
        return mongoTemplate.findById(id, FlightInformation.class);
    }
    
    public long countInternational() {
        Query internationalCount = Query.query(Criteria.where("type")
                .is(FlightType.International));
        return mongoTemplate.count(internationalCount, FlightInformation.class);
    }
    
    public List<FlightInformation> findByDeparture(String departure) {
        Query byDeparture = new Query()
                .addCriteria(Criteria.where("departure").is(departure));
        return mongoTemplate.find(byDeparture, FlightInformation.class);
    }
    
    public List<FlightInformation> findByDurationBetween(int minMinutes, int maxMinutes) {
        Query byDurationBetween = Query
                .query(Criteria.where("durationMin")
                        .gte(minMinutes)
                        .lte(maxMinutes))
                .with(Sort.by(Sort.Direction.DESC, "durationMin"));
        return mongoTemplate.find(byDurationBetween, FlightInformation.class);
                
    }
    
    public List<FlightInformation> findDelayedAtDeparture(String departure) {
        Query delayedAtDeparture = Query
                .query(Criteria.where("isDelayed").is(true)
                .and("departure").is(departure));
        return mongoTemplate.find(delayedAtDeparture, FlightInformation.class);
    }
    
    public List<FlightInformation> findRelatedToCityAndNotDelayed(String city) {
        Query byCity = Query.query(new Criteria()
                .orOperator(
                        Criteria.where("departure").is(city),
                        Criteria.where("destination").is(city))
                .andOperator(
                        Criteria.where("isDelayed").is(false)
                ));
        return mongoTemplate.find(byCity, FlightInformation.class);
    }
    
    public List<FlightInformation> findByAircraft(String aircraft) {
        Query byAircraft = Query.query(Criteria.where("aircraft.model").is(aircraft));
        return mongoTemplate.find(byAircraft, FlightInformation.class);
    }

    public List<FlightInformation> findByFreeText(String text) {
        TextCriteria textCriteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);
        Query byFreeText = TextQuery.queryText(textCriteria)
                .sortByScore()
                .with(PageRequest.of(0, 3));
        return this.mongoTemplate.find(byFreeText, FlightInformation.class);
    }
    
    public void createOne() {
        mongoTemplate.save(new FlightInformation());
    }
}