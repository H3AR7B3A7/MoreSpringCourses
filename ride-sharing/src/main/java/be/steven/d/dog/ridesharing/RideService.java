package be.steven.d.dog.ridesharing;

import org.springframework.stereotype.Service;

import java.util.List;

public interface RideService {
    List<Ride> getRides();
}