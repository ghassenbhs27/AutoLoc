package tn.esprit.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    private static final long SLOW_METHOD_THRESHOLD_MS = 1000;
    private static final long NANOS_PER_MILLISECOND = 1_000_000;

    @Around("execution(* tn.esprit.autoloc.domain.service..*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long durationNanos = System.nanoTime() - startTime;
            double durationMs = durationNanos / (double) NANOS_PER_MILLISECOND;
            String methodName = joinPoint.getSignature().toShortString();

            if (durationNanos > SLOW_METHOD_THRESHOLD_MS * NANOS_PER_MILLISECOND) {
                log.warn("{} a pris {} ms (seuil dépassé : {} ms)",
                        methodName, durationMs, SLOW_METHOD_THRESHOLD_MS);
            } else {
                log.info("{} exécutée en {} ms", methodName, durationMs);
            }
        }
    }
}
