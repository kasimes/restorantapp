package com.ornek.restorant.restorantapp.aspect;


import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceTracingAspect {

    private final Tracer tracer;

    public ServiceTracingAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Around("execution(* com.ornek.restorant.restorantapp.service..*(..))")
    public Object traceServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().toShortString();
        Span span = tracer.spanBuilder("service."+methodName).startSpan();
        try {
            return pjp.proceed();
        }catch (Throwable e){
            span.recordException(e);
            throw e;
        }
        finally {
            span.end();
        }
    }
}
