package Application.aspects;

import Application.services.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * LoggerAspect
 * Logs all method calls and exceptions for all classes except filters.
 *
 * @author Kollier Martin
 */


@Aspect
@Component
public class LoggerAspect {
    private final LoggerService loggerService;

    public LoggerAspect(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Pointcut("within(Application.*.*)")
    public void logAll() {

    }

    @Around("within(Application.*.*)")
    public void logAroundAll(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (Exception ignored){

        }
    }

    @AfterThrowing(pointcut = "logAll()", throwing = "e")
    public void logMethodException(JoinPoint jp, Throwable e) {
        String methodSig = extractMethodSignature(jp);
        loggerService.writeLog(String.format("%s was thrown in method %s with message: %s", e.getClass().getSimpleName(), methodSig, e.getMessage()), 3);
    }

    private String extractMethodSignature(JoinPoint jp) {
        return jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
    }
}