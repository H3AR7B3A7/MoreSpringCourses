package be.steven.d.dog.ridesharing.service.impl;

import be.steven.d.dog.ridesharing.model.Ride;
import be.steven.d.dog.ridesharing.repository.RideRepository;
import be.steven.d.dog.ridesharing.service.RideService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rideService")
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;

    public RideServiceImpl(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @Override
    public List<Ride> getRides() {
        return rideRepository.getRides();
    }

    @Override
    public Ride createRide(Ride ride) {
        return rideRepository.createRide(ride);
    }
}