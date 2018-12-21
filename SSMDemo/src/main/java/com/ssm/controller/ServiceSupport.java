package com.ssm.controller;

import com.sprucetec.commons.data.*;
import com.sprucetec.commons.data.exception.BusinessException;
import com.sprucetec.commons.data.exception.DuplicateKeyException;
import com.sprucetec.commons.data.exception.UniqueException;
import com.sprucetec.commons.data.model.*;
import com.sprucetec.commons.data.session.Session;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public  abstract class ServiceSupport<M extends BaseModel,Q extends Query,R> {
    @Inject
    private R repository ;
    @Inject
    protected Session session;


    /**
     * 添加实体
     * @param model
     * @return
     */
    protected M add (M model){
        if(model == null){
            return null;
        }
        if(repository instanceof Uniqueable){
            M amodel = ((Uniqueable<M> )repository).exists(model);
            if(amodel != null){
                if(amodel.getStatus() == BaseModel.DELETED){
                    if(this instanceof Lifeable){
                        ((Lifeable) this).enable(amodel);

                        if(repository instanceof Updatable){
                            model.setId(amodel.getId());
                            model.setUpdateBy(session.getUserId());
                            model.setUpdateTime(new Date());
                            ((Updatable ) repository).update(model);

                            return model;

                        }
                    }
                }
                throw new UniqueException("已经存在，请检查录入数据");
            }
        }
        if(model.getCreateTime() == null){
            model.setCreateTime(new Date());
        }
        if(model.getUpdateTime() == null){
            model.setUpdateTime(model.getCreateTime());
        }
        model.setCreateBy(session.getUserId());
        model.setUpdateBy(model.getCreateBy());
        try{
            ((Insertable<M>)repository).add(model);
        }catch (DuplicateKeyException e){
            throw new UniqueException("a");
        }
        return model;
    }

    /**
     * 实体不存在的时候添加
     * @param model
     * @return
     */
    protected M addIfNotExists(M model){
        if(model == null){
            return null;
        }
        if(repository instanceof Uniqueable){
            M aModel = ((Uniqueable<M>) repository).exists(model);
            if(aModel != null){
                if(aModel.getStatus() == BaseModel.DELETED && this instanceof Lifeable) {
                    enable(aModel);

                    if (repository instanceof Updatable) {
                        model.setId(aModel.getId());
                        model.setUpdateBy(session.getUserId());
                        model.setUpdateTime(new Date());

                        ((Updatable<M>) repository).update(model);
                        aModel = ((Uniqueable<M>) repository).exists(model);

                    }
                }
                return aModel;
            }


        }
        if(model.getCreateTime() == null){
            model.setCreateTime(new Date());
        }
        if(model.getUpdateTime() == null){
            model.setUpdateTime(model.getCreateTime());

        }
        model.setCreateBy(session.getUserId());
        model.setUpdateBy(model.getCreateBy());
        try{
            ((Insertable)repository).add(model);
        }catch (DuplicateKeyException e){
            M aModel = ((Uniqueable<M>)repository).exists(model);
            if(aModel != null){
                return aModel;
            }
            throw  new UniqueException("已经存在，请检查数据");
        }

        return model;

    }

    /**
     * 新增或修改，不存在，新增，否则，修改
     * @param model
     * @return
     */
    protected M addOrUpdate(M model){
        M aModel = ((Uniqueable<M>)repository).exists(model);
        if(aModel != null){
            if(aModel.getStatus() == BaseModel.DELETED && this instanceof Lifeable){
                enable(aModel);
            }
            model.setId(aModel.getId());
            update(model);
        }else {
            add(model);
        }
        return model;
    }

    /**
     * 更新实体
     * @param model
     * @return
     */
    protected int update(M model){
        if(model == null){
            return 0;
        }
        model.setUpdateTime(new Date());
        model.setUpdateBy(session.getUserId());
        try{
            return ((Updatable<M>)repository).update(model);
        }catch (DuplicateKeyException e){
            throw new UniqueException("数据存在重复，请检查数据");
        }
    }

    /**
     * 删除实体
     * @param model
     * @return
     */
    protected int delete(M model){
        if(model == null){
            return 0;
        }
        if(repository instanceof Referencable){
            Boolean isRefered = ((Referencable<M>)repository).isRefered(model);
            if(isRefered !=null && isRefered){
                throw new BusinessException("数据被引用了,不能被删除");
            }

        }
        model.setUpdateTime(new Date());
        model.setUpdateBy(session.getUserId());
        model.setStatus(BaseModel.DELETED);
        return ((Stateable<M>)repository).state(model);
    }

    /**
     * 启用实体
     * @param model
     * @return
     */
    protected  int enable(M model){
        if(model == null){
            return 0;
        }
        model.setUpdateBy(session.getUserId());
        model.setUpdateTime(new Date());
        model.setStatus(BaseModel.ENABLED);
        return ((Stateable<M>)repository).state(model);
    }

    /**
     * 禁用实体
     * @param model
     * @return
     */
    protected int disable(M model){
        if(model == null){
            return 0;
        }
        model.setUpdateBy(session.getUserId());
        model.setUpdateTime(new Date());
        model.setStatus(BaseModel.DISABLED);
        return ((Stateable<M>)repository).state(model);
    }

    /**
     * 实体状态转换
     * @param transition
     * @return
     */
    protected int transit(Transition<M> transition){
        if(transition == null){
            return 0;
        }
        M model = transition.getModel();
        if(model == null){
            return 0;
        }
        model.setUpdateTime(new Date());
        model.setUpdateBy(session.getUserId());
        return ((Stateable<M>)repository).transit(transition);
    }

    /**
     * 实体状态变化
     * @param model
     * @return
     */
    protected int state(M model){
        if(model == null){
            return 0;
        }
        model.setUpdateBy(session.getUserId());
        model.setUpdateTime(new Date());
        return ((Stateable<M>)repository).state(model);
    }

    /**
     * 删除历史数据
     * @param time
     * @return
     */
    protected int deleteBefore(Date time){
        if(time == null){
            return 0;
        }
        return ((Cleanable<M>)repository).deleteBefore(time);
    }

    /**
     * 查找实体
     * @param id
     * @return
     */
    protected M findById(long id){
        if(id <= 0){
            return null;
        }
        return ((Identifiable<M>)repository).findById(id);
    }

    protected M exists(M model){
        if(model == null){
            return null;
        }
        return ((Uniqueable<M>)repository).exists(model);
    }


    protected M findByCode(String code){
        if(code == null && code.isEmpty()){
            return null;
        }
        return ((Codeable<M>)repository).findByCode(code);
    }

    protected List<M> findByType(String type){
        if(type == null && type.isEmpty()){
            return new ArrayList<M>();
        }
        return ((Classifiable<M>)repository).findByType(type);
    }

    protected boolean isRefered(M model){
        if(model == null){
            return false;
        }
        return ((Referencable<M>)repository).isRefered(model);
    }

    protected List<M> findByState(int... states){
        if(states == null){
            return new ArrayList<M>();
        }
        return ((Stateable<M>)repository).findByState(states);

    }


    protected List<M> findAll(){
        return ((Listable<M>)repository).findAll();
    }

    protected Page<M> findByQuery(QPageQuery<Q> query){
        if(query == null){
            return null;
        }
        return ((Pageable<M,Q>)repository).findByQuery(query);
    }


    public void setRepository( R repository){
        this.repository = repository;
    }

    public void setSession(Session session){
        this.session = session;
    }

























}
