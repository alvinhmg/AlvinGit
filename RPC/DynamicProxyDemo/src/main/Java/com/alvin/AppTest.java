package com.alvin;

public class AppTest {
    /**
     * @Author hemingguang
     * @Description
     * 静态代理总结：
     *1、可以在不修改目标对象的功能前提下对目标功能的扩展
     * 2、缺点：因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
     * @Date 11:00 AM 2018/12/19
     * @Param [args]
     * @return void
     **/

    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //把目标对象传给代理对象，建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();


    }
}
