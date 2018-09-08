package com.maker.store.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {
    @Pointcut("within(com.maker.store.controller.StoreController)")
    public  void pointcut(){

    }

    @Before("pointcut()")
    public void before(){
        System.out.println("测试开始");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("测试结束");
    }
}
