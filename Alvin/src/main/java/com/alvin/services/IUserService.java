package com.alvin.services;

import com.alvin.model.User;
import com.sprucetec.commons.data.*;
import com.sprucetec.commons.data.model.QKeyword;

import java.util.List;

public interface IUserService  extends Addable<User>,Identifiable<User>,Updatable<User>,Lifeable<User>,Pageable<User,QKeyword>
,Uniqueable<User>,Listable<User>,Deletable<User> {

    public User selectUser(long userId);

    public User addIfNotExists(User user);

    public int update(User user);

    public int enable(User user);

    public int disable(User user);

    public List<User> findAll();

}
