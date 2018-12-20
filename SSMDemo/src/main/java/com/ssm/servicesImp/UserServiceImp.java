package com.ssm.servicesImp;

import com.sprucetec.commons.data.exception.BusinessException;
import com.sprucetec.commons.data.exception.DuplicateKeyException;
import com.sprucetec.commons.data.exception.RepositoryException;
import com.sprucetec.commons.data.exception.UniqueException;
import com.sprucetec.commons.data.model.Page;
import com.sprucetec.commons.data.model.QKeyword;
import com.sprucetec.commons.data.model.QPageQuery;
import com.sprucetec.commons.data.support.ServiceSupport;
import com.sprucetec.commons.util.StringUtils;
import com.ssm.model.User;
import com.ssm.repository.UserRepository;
import com.ssm.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static com.sprucetec.commons.data.model.BaseModel.DELETED;


@Service("UserService")

public class UserServiceImp extends ServiceSupport<User, QKeyword, UserRepository> implements UserService {


    public int insert(User user) {
        return repository.insertUser(user);
    }

    @Override
    public User addIfNotExists(User user) {
        if (user == null) {
            return null;
        }

        User aModel = super.exists(user);
        if (aModel != null) {
            if (aModel.getStatus() == DELETED) {
                enable(aModel);
            }

            user.setId(aModel.getId());
            if (aModel.getSign() != user.getSign()) {
                update(user);
            }
            return user;
        }

        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        if (user.getUpdateTime() == null) {
            user.setUpdateTime(user.getCreateTime());
        }
        user.setCreateBy(session.getUserId());
        user.setUpdateBy(user.getCreateBy());

        try {
            super.add(user);
        } catch (DuplicateKeyException e) {
            return super.exists(user);
        }

        return user;
    }

    @Override
    public User findByCode(String code) throws RepositoryException {
        if (StringUtils.isNull(code)) {
            return null;
        }
        User user = repository.exists(new User(code));
        return (user == null || user.getStatus() == DELETED) ? null : user;
    }

    public User queryByUsername(String username) {
        return repository.selectByUserId(username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int update(User user) throws UniqueException, BusinessException, RepositoryException {
        return super.update(user);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int delete(User user) throws BusinessException, RepositoryException {
        return super.delete(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int enable(User user) throws RepositoryException {
        return super.enable(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int disable(User user) throws RepositoryException {
        return super.disable(user);
    }

    @Override
    public User findById(long id) {
        return super.findById(id);
    }

    @Override
    public User exists(User user) throws RepositoryException {
        return super.exists(user);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public Page<User> findByQuery(QPageQuery<QKeyword> query) throws RepositoryException {
        return super.findByQuery(query);
    }
}
