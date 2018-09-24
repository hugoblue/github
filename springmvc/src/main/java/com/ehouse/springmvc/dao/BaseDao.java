package com.ehouse.springmvc.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

    T save(T entity);

    void update(T entity);

    void partUpdate(int id, String[] names, Object[] values);

    void delete(Serializable id);

    T findById(Serializable id);

    List<T> findAll();

    List<T> findByHQL(String hql, Object... params);

    List<T> queryPage(String hql, int pageNo, int pageSize);

}
