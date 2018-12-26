package com.alvin.dao;

import com.alvin.model.User;
import com.sprucetec.commons.data.*;
import com.sprucetec.commons.data.model.QKeyword;


public interface IUserDao extends Identifiable<User>,Updatable<User>,Stateable<User>,Uniqueable<User>,Insertable<User>,
Pageable<User,QKeyword>,Listable<User> ,Deletable<User> {

    User selectUser(long id);

    int addUser(User user);

    int updateUser(User user);



}
