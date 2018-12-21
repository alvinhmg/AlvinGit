package com.alvin.dao;

import com.alvin.model.User;

public interface IUserDao {

    User selectUser(long id);

}
