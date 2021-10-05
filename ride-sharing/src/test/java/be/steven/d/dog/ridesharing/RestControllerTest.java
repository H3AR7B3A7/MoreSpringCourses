package be.steven.d.dog.ridesharing;

import be.steven.d.dog.ridesharing.model.Ride;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestControllerTest {
    
    private final RestTemplate restTemplate;

    public RestControllerTest() {
        this.restTemplate = new RestTemplate();
    }
    
    @Test(timeout = 3000)
    public void testGetRides() {
        ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
                "http://localhost:8080/rides", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Ride>>() {
                });
        List<Ride> rides = ridesResponse.getBody();

        for (Ride ride : rides) {
            System.out.println("Ride name: " + ride.getName());
        }
    }

    @Test(timeout = 3000)
    public void testCreateRide() {
        Ride ride = new Ride();
        ride.setName("Antwerp - Brussels");
        ride.setDuration(60);

        ride = restTemplate.postForObject("http://localhost:8080/rides", ride, Ride.class);

        System.out.println("Ride: " + ride);
    }
}