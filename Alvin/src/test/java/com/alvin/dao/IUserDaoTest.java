package com.alvin.dao;

import com.alvin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDaoTest {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(IUserDaoTest.class);

    @Autowired
    private IUserDao dao;

    @Test
    public void testSelectUser() throws Exception {
        long id = 1;
        User user = dao.selectUser(id);
        logger.info(user.getUsername());
    }

    @Test
    public void testAddUser(){
        User user = new User();
        user.setAge(20);
        user.setEmail("lisi@163.com");
        user.setPassword("123456");
        user.setRole("root");
        user.setSex("男");
        user.setUsername("lisi");
        user.setStatus(1);
        user.setRegIp("127.0.0.1");
        int userAdd = dao.addUser(user);
        long id = user.getId();
        User useradd = dao.selectUser(id);
        logger.info(String.valueOf(userAdd));
        logger.info(useradd.getUsername());
    }

    @Test
    public void testUpdateUser(){
        long id = 2;
        User user = dao.selectUser(id);
        logger.info(user.getUsername());
        user.setAge(18);
        user.setEmail("wangwu@163.com");
        user.setPassword("123456");
        user.setRole("root");
        user.setSex("男");
        user.setUsername("wangwu");
        user.setStatus(1);
//        user.setUpdateTime(new Date());
        int userUpdate = dao.update(user);
        logger.info(String.valueOf(userUpdate));
        logger.info(user.getUsername());
    }

   /* @Test
    public void testDeleteUser(){

    }*/

}