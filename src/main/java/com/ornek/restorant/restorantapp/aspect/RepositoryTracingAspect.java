package com.ornek.restorant.restorantapp.aspect;


import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryTracingAspect {

    private final Tracer tracer;

    public RepositoryTracingAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Around("execution(* com.ornek.restorant.restorantapp.repository..*(..))")
    public Object traceRepositoryMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Span span = tracer.spanBuilder("jpa." + methodName).startSpan();
        try {
            return joinPoint.proceed();
        } finally {
            span.end();
        }
    }
}
