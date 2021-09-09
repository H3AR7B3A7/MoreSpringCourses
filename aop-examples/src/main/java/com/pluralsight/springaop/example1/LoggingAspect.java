package com.pluralsight.springaop.example1;

import java.util.logging.Logger;

public class LoggingAspect {
    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    public void before() {
        logger.info("Entering method...");
    }

    public void after() {
        logger.info("Exiting method...");
    }
}