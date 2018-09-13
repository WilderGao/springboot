package com.qg.springboot.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author WilderGao
 * time 2018-09-13 22:05
 * motto : everything is no in vain
 * description cglib反射实现
 */
public class CglibProxyTest implements MethodInterceptor {

    private CglibProxyTest(){}

    public static <T extends Target> Target newProxyInstance(Class<T> targetInstanceClazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetInstanceClazz);
        enhancer.setCallback(new CglibProxyTest());
        return (Target) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(o, objects);
    }
}
