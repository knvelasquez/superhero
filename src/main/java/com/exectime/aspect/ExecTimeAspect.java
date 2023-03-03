package com.exectime.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecTimeAspect {

    private static final Logger logger = LogManager.getLogger(ExecTimeAspect.class);

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

            final StringBuilder startOutput = new StringBuilder("@ExecTime starting measurement: ")
                    .append(className)
                    .append(".")
                    .append(methodName);

            logger.info(startOutput.toString());
            Object proceed = entryPoint.proceed();

            final String millisecond = String.valueOf((System.currentTimeMillis() - startTime));
            final StringBuilder endOutput = new StringBuilder("@ExecTime end measurement: ")
                    .append(millisecond)
                    .append("milliseconds");

            logger.info(endOutput.toString());
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}