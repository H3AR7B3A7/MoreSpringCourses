package com.pluralsight.springaop.flightsmanagement.dao;

import com.pluralsight.springaop.flightsmanagement.domain.Passenger;

public interface PassengerDao {
    Passenger getPassenger(int id);
}