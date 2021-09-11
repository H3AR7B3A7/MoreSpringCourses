package com.pluralsight.springaop.example4;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    private final Logger logger = Logger.getLogger(com.pluralsight.springaop.example3.LoggingAspect.class.getName());

    @Around("@annotation(com.pluralsight.springaop.example4.Log)")
    public Object log (ProceedingJoinPoint thisJointPoint) throws Throwable {
        String methodName = thisJointPoint.getSignature().getName();
        Object[] methodArgs = thisJointPoint.getArgs();
        logger.info("Call method " + methodName + " with arg " + methodArgs[0]);
        Object result = thisJointPoint.proceed();
        logger.info("Method " + methodName + " returns " + result);
        return result;
    }
}