package com.ssm.dao;

import com.ssm.model.User;

public interface UserDao {

    public User selectByUserId(String username);

    public int insertUser(User user);

}