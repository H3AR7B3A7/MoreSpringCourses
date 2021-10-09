package be.steven.d.dog.ridesharing.service.impl;

import be.steven.d.dog.ridesharing.model.Ride;
import be.steven.d.dog.ridesharing.repository.RideRepository;
import be.steven.d.dog.ridesharing.service.RideService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public Ride getRide(Integer id) {
        return rideRepository.getRide(id);
    }

    @Override
    public Ride updateRide(Ride ride) {
//        Ride rideToUpdate = getRide(ride.getId());
//        rideToUpdate.setName(ride.getName());
//        rideToUpdate.setDuration(ride.getDuration());
        return rideRepository.updateRide(ride);
    }

    @Override
    public void batch() {
        List<Ride> rides = rideRepository.getRides();
        List<Object[]> pairs = new ArrayList<>();
        
        for (Ride ride : rides) {
            Object[] tmp = {
                    new Date(), ride.getId()
            };
            pairs.add(tmp);
        }
        
        rideRepository.updateRides(pairs);
    }

    @Override
    @Transactional
    public void failingBatch() {
        List<Ride> rides = rideRepository.getRides();
        List<Object[]> pairs = new ArrayList<>();

        for (Ride ride : rides) {
            Object[] tmp = {
                    new Date(), ride.getId()
            };
            pairs.add(tmp);
        }
        
        rideRepository.updateRides(pairs);
        throw new RuntimeException("Testing transactional exception handling...");
    }

    @Override
    public void deleteRide(Integer id) {
        rideRepository.deleteRide(id);
    }
}