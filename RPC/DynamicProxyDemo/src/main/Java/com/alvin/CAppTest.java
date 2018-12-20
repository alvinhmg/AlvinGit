package com.alvin;
/**
 * @Author hemingguang
 * @Description
 * Cglib 代理：
 *     如果加入容器的目标对象有实现接口,用JDK代理
 * 　　如果目标对象没有实现接口,用Cglib代理 　　
 * 　　如果目标对象实现了接口，且强制使用cglib代理，则会使用cglib代理
 * @Date 12:09 PM 2018/12/19
 * @Param
 * @return
 **/

public class CAppTest {
    public static void main(String[] args) {
        //目标对象
        CUserDao target = new CUserDao();
        //代理对象
        CUserDao proxy = (CUserDao) new CProxyFactory(target).getProxyInstance();

        proxy.save();

    }
}
