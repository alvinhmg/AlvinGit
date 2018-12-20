package com.ssm.repository;

import com.sprucetec.commons.data.*;
import com.sprucetec.commons.data.annotation.Repository;
import com.sprucetec.commons.data.model.QKeyword;
import com.ssm.model.User;

@Repository
public interface UserRepository extends Identifiable<User>,Updatable<User>,Stateable<User>,Uniqueable<User>,
        Insertable<User>,Pageable<User,QKeyword>,Listable<User> {

    public User selectByUserId(String username);

    public int insertUser(User user);

}
