package com.qg.springboot.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WilderGao
 * time 2018-09-13 22:02
 * motto : everything is no in vain
 * description jdk反射实现
 */
public class JdkDynamicProxyTest implements InvocationHandler {

    private Target target;

    public JdkDynamicProxyTest(Target target) {
        this.target = target;
    }

    public static Target newProxyInstance(Target target){
        return (Target) Proxy.newProxyInstance(JdkDynamicProxyTest.class.getClassLoader(),
                new Class<?>[]{Target.class},
                new JdkDynamicProxyTest(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
