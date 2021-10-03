package be.steven.d.dog.ridesharing.service;

import be.steven.d.dog.ridesharing.model.Ride;

import java.util.List;

public interface RideService {
    List<Ride> getRides();
}