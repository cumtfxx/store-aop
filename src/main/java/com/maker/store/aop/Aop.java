package com.maker.store.aop;

import com.maker.store.StoreApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Aspect
@Component
public class Aop {
    private Logger logger=LoggerFactory.getLogger(StoreApplication.class);

    @Pointcut("@within(org.springframework.stereotype.Service))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        this.logger.info(methodName+"开始时间："+new Date());
    }

    @After("pointcut()")
    public void after(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        this.logger.info(methodName+"结束时间："+new Date());
    }
}
