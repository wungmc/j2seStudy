package com.wung.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * Created by wung on 2016/5/11.
 */
public class InvocationHandlerImpl implements InvocationHandler {
    //真实的代理对象
    private Object target;

    public InvocationHandlerImpl(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();//在调用真实方法之前做一些事情
        method.invoke(target, args);
        after();
        return null;
    }

    private void before() {
        System.out.println("before........");
    }

    private void after() {
        System.out.println("after........");
    }
}
