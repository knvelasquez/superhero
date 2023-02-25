package com.exectime.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecTimeAspect {

    @Pointcut("@annotation(com.exectime.api.ExecTime)")
    public void handlingTimePointcut() {
    }

    @Around("handlingTimePointcut()")
    public Object handlingTimeAround(ProceedingJoinPoint entryPoint) {
        try {
            long startTime = System.currentTimeMillis();
            MethodSignature sign = (MethodSignature) entryPoint.getSignature();
            String methodName = sign.getMethod().getName();
            String className = entryPoint.getTarget().getClass().getCanonicalName();
            System.out.println("@ExecTime starting measurement:".concat(className.concat(".").concat(methodName)));
            Object proceed = entryPoint.proceed();
            System.out.println("@ExecTime end measurement: ".concat(String.valueOf((System.currentTimeMillis() - startTime))).concat(" milliseconds"));
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}