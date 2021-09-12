package com.pluralsight.springaop.flightsmanagement.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
@Order(1)
public class LoggingAspect1 {
    private final Logger logger = Logger.getLogger(LoggingAspect1.class.getName());
    
    @Before("execution(public String com.pluralsight.springaop.flightsmanagement.domain.Flight.getId())")
    public void loggingAdviceGetId() {
        logger.info("Flight getId method will be called...");
    }
    
    @AfterReturning("execution(public * *.print())") // Why are these printing before print execution???
    public void loggingAdvicePrint() {
        logger.warning("A print method has been called...");
    }
    
    @Pointcut("within(com.pluralsight.springaop.flightsmanagement.domain.Ticket)")
    public void allTicketMethods() { }
    
    @After("allTicketMethods()")
    public void loggingAdvice(JoinPoint joinPoint) {
        logger.info("A ticket method has been called...");
        logger.info(joinPoint.toString());
        logger.info(joinPoint.getTarget().toString());
    }
}