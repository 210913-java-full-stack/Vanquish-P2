package Application.aspects;

import Application.services.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * LoggerAspect
 * Logs all method calls and exceptions for all classes except filter and configuration files.
 *
 * @author Kollier Martin
 * @date 11/10/2021
 */

@Aspect
@Component
public class LoggerAspect {
    private final LoggerService loggerService;

    public LoggerAspect(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Pointcut("within(Application.*.*) && !within(Application.filters..*) && !within(Application.configs..*)")
    public void logAll() {

    }

    @Around("within(Application.*.*) && !within(Application.filters..*) && !within(Application.configs..*)")
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
