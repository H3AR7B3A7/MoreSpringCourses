package be.steven.d.dog.ridesharing.controller;

import be.steven.d.dog.ridesharing.model.Ride;
import be.steven.d.dog.ridesharing.service.RideService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RideController {
    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @RequestMapping(value = "/rides", method = RequestMethod.GET)
    public @ResponseBody
    List<Ride> getRides() {
        return rideService.getRides();
    }
    
    @PostMapping("/rides")
    public @ResponseBody Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }
    
    @GetMapping("/rides/{id}")
    public @ResponseBody Ride getRide(@PathVariable Integer id) {
        return rideService.getRide(id);
    }
    
    @PutMapping("/rides")
    public @ResponseBody Ride updateRide(@RequestBody Ride ride) {
        return rideService.updateRide(ride);
    }
}