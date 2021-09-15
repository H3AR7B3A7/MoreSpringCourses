package com.pluralsight.springaop.flightsmanagement.aspects;

import com.pluralsight.springaop.flightsmanagement.exceptions.CountryDoesNotExistException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

@Aspect
public class CountryDoesNotExistAspect {
    private final Logger logger = Logger.getLogger(CountryDoesNotExistAspect.class.getName());
    
    @AfterThrowing(pointcut = "execution(* com.pluralsight.springaop.flightsmanagement.dao.impl.PassengerDaoImpl.insert(..))", throwing="ex")
    public void log(CountryDoesNotExistException ex) {
        logger.severe("Attempt to insert passenger with an unexisting country code detected...");
    }
}