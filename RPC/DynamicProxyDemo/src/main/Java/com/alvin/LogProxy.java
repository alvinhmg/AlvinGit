package com.alvin;

import com.alvin.annotation.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogProxy implements InvocationHandler {
    //1.声明被代理的对象
    private Object src;

    //2.在私有的构造方法中给成员设置值
    private LogProxy (Object src){
        this.src = src;
    }

    //3.提供一个静态方法返回代理对象

    public static Object factory(Object src){
        //生成一个代理类接口的子类
        Object proxyObj = Proxy.newProxyInstance(LogProxy.class.getClassLoader(),src.getClass().getInterfaces()
        ,new LogProxy(src));
        return proxyObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果该方法带有Log注解，先执行Logger操作再执行该方法
        if(method.isAnnotationPresent(Log.class)){
            System.out.println("this is log statement!!!");
            return method.invoke(src,args);
        }else {
            return method.invoke(src,args);
        }
    }
}
