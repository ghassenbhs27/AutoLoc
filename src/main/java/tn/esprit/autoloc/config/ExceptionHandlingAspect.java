package tn.esprit.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandlingAspect {

    @AfterThrowing(
            pointcut = "execution(* tn.esprit.autoloc.domain.service..*(..))",
            throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String exceptionType = exception.getClass().getSimpleName();
        String errorMessage = exception.getMessage();

        log.error("Exception dans la méthode {} — type : {} — message : {}",
                methodName, exceptionType, errorMessage);
    }
}
