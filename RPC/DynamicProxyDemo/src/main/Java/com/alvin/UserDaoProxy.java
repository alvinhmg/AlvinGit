package com.alvin;
/**
 * @Author hemingguang
 * @Description 代理对象--静态代理
 * @Date 10:36 AM 2018/12/19
 * @Param []
 * @return void
 **/
public class UserDaoProxy implements IUserDao {
    /**
     * @Author hemingguang
     * @Description 接受目标保存对象
     * @Date 10:38 AM 2018/12/19
     * @Param
     * @return
     **/


    private IUserDao target;

    public UserDaoProxy(IUserDao target){
        this.target = target;
    }


    @Override
    public void save() {
        System.out.println("开始事务...");
        target.save();//执行目标对象的方法
        System.out.println("提交事务...");
    }
}
