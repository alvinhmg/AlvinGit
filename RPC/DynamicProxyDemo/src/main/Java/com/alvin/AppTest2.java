package com.alvin;

/**
 * @Author hemingguang
 * @Description //TODO meicai
 * 动态代理总结：
 * 代理对象不用实现接口，但是目标对象一定要实现接口否侧不能使用动态代理
 * @Date 11:27 AM 2018/12/19
 * @Param
 * @return
 **/

public class AppTest2 {
    public static void main(String[] args) {
        //目标对象
        IUserDao target = new UserDao();
        // 【原始的类型
        System.out.println(target.getClass());
        //给目标对象创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());
        proxy.save();

    }
}
