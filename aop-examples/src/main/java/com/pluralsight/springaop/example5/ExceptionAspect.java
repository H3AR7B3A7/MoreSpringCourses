package com.pluralsight.springaop.example5;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Aspect
@Service
public class ExceptionAspect {
    private final Logger logger = Logger.getLogger(ExceptionAspect.class.getName());
    
    @AfterThrowing(pointcut = "execution(* *(..))", throwing = "exception")
    public void processException(RuntimeException exception) throws Throwable {
        logger.severe(exception.getMessage());
    }
}