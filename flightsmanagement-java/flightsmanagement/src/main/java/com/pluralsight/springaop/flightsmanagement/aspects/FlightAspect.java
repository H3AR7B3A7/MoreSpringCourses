package com.pluralsight.springaop.flightsmanagement.aspects;

import com.pluralsight.springaop.flightsmanagement.flyer.Flyer;
import com.pluralsight.springaop.flightsmanagement.flyer.FlyerImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class FlightAspect {
    @DeclareParents(value="com.pluralsight.springaop.flightsmanagement.domain.Flight",
            defaultImpl= FlyerImpl.class)
    private Flyer flight;
}
