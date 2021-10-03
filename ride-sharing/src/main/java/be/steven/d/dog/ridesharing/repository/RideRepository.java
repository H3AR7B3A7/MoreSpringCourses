package be.steven.d.dog.ridesharing.repository;

import be.steven.d.dog.ridesharing.model.Ride;

import java.util.List;

public interface RideRepository {
    List<Ride> getRides();
}