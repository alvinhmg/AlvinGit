package com.ssm.services;

import com.sprucetec.commons.data.*;
import com.sprucetec.commons.data.exception.BusinessException;
import com.sprucetec.commons.data.exception.RepositoryException;
import com.sprucetec.commons.data.model.QKeyword;
import com.ssm.model.User;

import java.util.List;

public interface UserService extends Identifiable<User>,Updatable<User>,Lifeable<User>,Pageable<User,QKeyword>,Uniqueable<User>,
        Listable<User>,Deletable<User> {

    public User queryByUsername(String username);

    public int insert(User user);



    public User findByCode(String code) throws RepositoryException;



}
