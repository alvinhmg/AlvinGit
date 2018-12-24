package com.alvin.services.impl;

import com.alvin.dao.IUserDao;
import com.alvin.model.User;
import com.alvin.services.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {

        return this.userDao.selectUser(userId);
    }
}
