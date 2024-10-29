package dev.almasabdykadyr.library.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut(
            "execution(* dev.almasabdykadyr.library.service.BookRentalService.*(..)) || " +
            "execution(* dev.almasabdykadyr.library.notification.NotificationProducer.*(..))"
    )
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Executing: {} with arguments = {}",
                joinPoint.getSignature(),
                joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Completed: {} with result = {}",
                joinPoint.getSignature(), result);
    }

    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        log.info("{} executed in {} ms", joinPoint.getSignature(), duration);
        return result;
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in {} with message = {}",
                joinPoint.getSignature(), ex.getMessage(), ex);
    }
}

