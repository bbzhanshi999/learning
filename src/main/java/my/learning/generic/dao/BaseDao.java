package my.learning.generic.dao;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public interface BaseDao<T> {

    public T find(T entity);

    public void save(T entity);

}
