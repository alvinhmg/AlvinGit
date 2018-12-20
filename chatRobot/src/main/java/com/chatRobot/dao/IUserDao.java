package com.chatRobot.dao;

import com.chatRobot.model.User;

import java.util.List;

public interface IUserDao {

    User selectUser(long id);

    List<User> selectAllUser();

    User selectUserByEmail(String email);

    User addUser(User user);

    User removeUser(long id);

    User modifyUser(User user);

}