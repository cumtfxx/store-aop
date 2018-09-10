package com.maker.store.aop;

import com.maker.store.StoreApplication;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Aspect
@Component
public class Aop {
    private Logger logger=LoggerFactory.getLogger(StoreApplication.class);

    @Pointcut("within(com.maker.store.controller.StoreController)")
    public  void pointcut(){
    }

    @Before("pointcut()")
    public void before(){
        this.logger.info("开始时间："+new Date());
    }

    @After("pointcut()")
    public void after(){
        this.logger.info("结束时间："+new Date());
    }
}
