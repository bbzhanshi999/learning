package my.learning.generic.service;

import my.learning.generic.dao.BaseDao;
import my.learning.generic.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public abstract class BaseService<D extends BaseDao<E>,E extends BaseEntity> {

    @Autowired
    D dao;

    public void save(E entity){
        dao.save(entity);
    }

    public E find(E entity){
        return  dao.find(entity);
    }

}
