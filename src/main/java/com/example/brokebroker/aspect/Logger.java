package com.example.brokebroker.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log
@Aspect
@Component
public class Logger {

    /**
     * Method to log used methods in services and repositories packages
     */
    @Before("execution(* com.example.brokebroker.services..*.*(..)) || " +
            "execution(* com.example.brokebroker.repositories..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget().getClass().getSimpleName()
                + " invokes method " + joinPoint.getSignature().getName()
                + Arrays.toString(joinPoint.getArgs()));
    }
}