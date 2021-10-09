package be.steven.d.dog.ridesharing.controller;

import be.steven.d.dog.ridesharing.model.Ride;
import be.steven.d.dog.ridesharing.service.RideService;
import be.steven.d.dog.ridesharing.util.ServiceError;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    
    @GetMapping("/rides/batch")
    public @ResponseBody Object batch() {
        rideService.batch();
        return null;
    }

    @GetMapping("/rides/failing-batch")
    public @ResponseBody Object failingBatch() {
        rideService.failingBatch();
        return null;
    }
    
    @DeleteMapping("/rides/{id}")
    public @ResponseBody Object deleteRide(@PathVariable(value = "id") Integer id) {
        rideService.deleteRide(id);
        return null;
    }

    @GetMapping("/rides/test")
    public @ResponseBody Ride exceptionTest() throws Exception {
        throw new DataAccessException("Some exception message..."){};
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handleExceptions(RuntimeException ex) {
        ServiceError serviceError = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(serviceError, HttpStatus.OK);
    }
}