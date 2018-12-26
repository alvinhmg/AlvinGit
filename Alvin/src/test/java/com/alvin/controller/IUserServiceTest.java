package com.alvin.controller;

import com.alvin.model.User;
import com.alvin.services.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IUserServiceTest {

    @Autowired
    private IUserService iUserService;

    @Test
    public void TestUserServiceById() throws Exception{
        long id = 1;
        User user = iUserService.selectUser(id);
        System.out.println(user.getUsername());

    }
}
