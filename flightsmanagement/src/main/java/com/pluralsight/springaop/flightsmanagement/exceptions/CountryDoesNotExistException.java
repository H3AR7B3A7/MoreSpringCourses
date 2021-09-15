package com.pluralsight.springaop.flightsmanagement.exceptions;

public class CountryDoesNotExistException extends RuntimeException {
    private String countryCode;

    public CountryDoesNotExistException(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getCountryCode() {
        return countryCode;
    }
}