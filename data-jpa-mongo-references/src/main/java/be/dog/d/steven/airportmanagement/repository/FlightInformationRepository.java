package be.dog.d.steven.airportmanagement.repository;

import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> {
    List<FlightInformation> findByDepartureCityAndDestinationCity(String dep, String dst);

    @Query("{'aircraft.nbSeats' : {$gte: ?0}}")
    List<FlightInformation> findByMinAircraftNbSeats(int i);
}