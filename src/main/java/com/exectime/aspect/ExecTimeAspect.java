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

            final String startOutput = String.format("@ExecTime starting measurement:%s.%s", className, methodName);
            System.out.println(startOutput);
            Object proceed = entryPoint.proceed();

            final String millisecond = String.valueOf((System.currentTimeMillis() - startTime));
            final String endOutput = String.format("@ExecTime end measurement: %s milliseconds", millisecond);
            System.out.println(endOutput);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}