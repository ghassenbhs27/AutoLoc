package tn.esprit.autoloc.config;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before ("execution(* tn.esprit.autoloc.domain.service..*(..))")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        log.info("Début de {} avec les paramètres : {}", methodName, Arrays.deepToString(arguments));
    }

    @After("execution(* tn.esprit.autoloc.domain.service..*(..))")
    public void logAfterAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Fin de la méthode {}", methodName);
    }
}
