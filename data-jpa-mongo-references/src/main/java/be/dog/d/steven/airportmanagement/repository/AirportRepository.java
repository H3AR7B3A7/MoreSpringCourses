package be.dog.d.steven.airportmanagement.repository;

import be.dog.d.steven.airportmanagement.domain.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> { }