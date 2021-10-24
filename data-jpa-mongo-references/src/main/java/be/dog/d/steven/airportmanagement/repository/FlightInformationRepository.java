package be.dog.d.steven.airportmanagement.repository;

import be.dog.d.steven.airportmanagement.domain.FlightInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> { }