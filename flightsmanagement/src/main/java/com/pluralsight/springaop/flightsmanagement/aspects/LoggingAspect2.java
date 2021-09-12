package com.pluralsight.springaop.flightsmanagement.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
@Order(2)
public class LoggingAspect2 {
    private Logger logger = Logger.getLogger(LoggingAspect2.class.getName());
    
    @Pointcut("execution(* com.pluralsight.springaop.flightsmanagement.domain.*.*set*(..))")
    public void allSetters() { }
    
    @Around("allSetters()")
    public Object log(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();
        logger.severe("Call method " + methodName + " with arg " + methodArgs[0]);
        Object result = thisJoinPoint.proceed();
        logger.info("Method " + methodName + " returns " + result);
        return result;
    }
}