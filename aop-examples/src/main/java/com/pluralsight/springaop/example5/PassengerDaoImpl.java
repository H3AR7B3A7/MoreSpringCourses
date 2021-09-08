package com.pluralsight.springaop.example5;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("passengerDao")
public class PassengerDaoImpl implements PassengerDao {
	
	private static Map<Integer, Passenger> passengersMap = new HashMap<>();
	
	public Passenger getPassenger(int id) {
		if (null != passengersMap.get(id)) {
			return passengersMap.get(id);
		}

		if (id<1) throw new RuntimeException("incorrect id");
		Passenger passenger = new Passenger(id);
		passengersMap.put(id, passenger);

		return passenger;
	}

}
