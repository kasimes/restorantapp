package com.ornek.restorant.restorantapp.aspect;


import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerTracingAspect {

    private final Tracer tracer;

    public ControllerTracingAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Around("execution(* com.ornek.restorant.restorantapp.controller..*(..))")
    public Object traceControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Span span = tracer.spanBuilder("controller." + methodName).startSpan();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
    }
}
