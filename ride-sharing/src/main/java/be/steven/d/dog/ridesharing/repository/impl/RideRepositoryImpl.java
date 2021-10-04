package be.steven.d.dog.ridesharing.repository.impl;

import be.steven.d.dog.ridesharing.model.Ride;
import be.steven.d.dog.ridesharing.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Ride> getRides() {
        Ride ride = new Ride();
        ride.setName("Corner Canyon");
        ride.setDuration(120);
        List <Ride> rides = new ArrayList<>();
        rides.add(ride);
        return rides;
    }
}