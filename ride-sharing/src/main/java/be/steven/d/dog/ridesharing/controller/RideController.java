package be.steven.d.dog.ridesharing.controller;

import be.steven.d.dog.ridesharing.service.RideService;
import be.steven.d.dog.ridesharing.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RideController {
    @Autowired
    private RideService rideService;

    @RequestMapping(value = "/rides", method = RequestMethod.GET)
    public @ResponseBody
    List<Ride> getRides() {
        return rideService.getRides();
    }
}