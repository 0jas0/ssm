package com.jas.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AspectTime {
    @Pointcut("@within(com.jas.web.annotation.MethodTime) || @annotation(com.jas.web.annotation.MethodTime)")
    public void AspectConfigTime(){
    }

    @Around("AspectConfigTime()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println(joinPoint);
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        }finally {
            long endTime = System.currentTimeMillis();
            System.out.println(method.getName()+"方法执行了"+(endTime - startTime)/1000 + "s");
        }
    }
}
