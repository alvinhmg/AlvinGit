package com.alvin.services.impl;

import com.alvin.dao.IUserDao;
import com.alvin.model.User;
import com.alvin.services.IUserService;
import com.sprucetec.commons.data.exception.BusinessException;
import com.sprucetec.commons.data.exception.DuplicateKeyException;
import com.sprucetec.commons.data.exception.RepositoryException;
import com.sprucetec.commons.data.exception.UniqueException;
import com.sprucetec.commons.data.model.Page;
import com.sprucetec.commons.data.model.QKeyword;
import com.sprucetec.commons.data.model.QPageQuery;
import com.sprucetec.commons.data.support.ServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.sprucetec.commons.data.model.BaseModel.DELETED;

@Service("userService")
public class UserServiceImpl extends ServiceSupport<User,QKeyword,IUserDao> implements IUserService {

//    @Resource
//    private IUserDao userDao;

    public User selectUser(long userId) {

        return this.repository.selectUser(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int delete(User user) throws BusinessException, RepositoryException {
        return super.delete(user);
    }

    @Override
    public User addIfNotExists(User user) {
        if(user == null){
            return null;
        }
        User aModel = repository.exists(user);
        if(aModel != null){
            if(aModel.getStatus() == DELETED){
                enable(aModel);
            }
            user.setId(aModel.getId());
            return user;
        }
        if(user.getCreateTime() == null){
            user.setCreateTime(new Date());
        }
        if(user.getUpdateTime() == null){
            user.setCreateTime(new Date());
        }
        user.setCreateBy(user.getCreateBy());

        try{
            repository.addUser(user);
        }catch (DuplicateKeyException e){
            return super.exists(user);
        }
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int enable(User user) throws RepositoryException {
        return super.enable(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int disable(User user) throws RepositoryException {
        return super.disable(user);
    }


    @Override
    public User findById(long id) throws RepositoryException {
        return super.findById(id);
    }

    @Override
    public List<User> findAll() throws RepositoryException {
        return super.findAll();
    }

    @Override
    public Page<User> findByQuery(QPageQuery<QKeyword> query) throws RepositoryException {
        return super.findByQuery(query);
    }

    @Override
    public User exists(User user) throws RepositoryException {
        return super.exists(user);
    }

    @Override
    public int update(User user) throws BusinessException, RepositoryException {
        return super.update(user);
    }

    @Override
    public User add(User user) throws UniqueException, BusinessException, RepositoryException {
        return super.add(user);
    }
}
