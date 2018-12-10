package com.qg.springboot.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author WilderGao
 * time 2018-09-15 13:49
 * motto : everything is no in vain
 * description
 */
@Component
@Aspect
public class NotVeryUsefulAspect {

    @Pointcut("execution(* com.qg.springboot.aop.*.*(..))")
    private void anyOldTransfer(){
        System.out.println("调用aspect-----");
    }

    public static void main(String[] args) {

    }

}
