package com.ehouse.springmvc.dao.impl;

import com.ehouse.springmvc.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Class<?> clz;

    public Class<?> getClz() {
        if (clz == null) {
            //获取泛型的Class对象
            clz = ((Class<?>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
                    .getActualTypeArguments()[0]));
        }
        return clz;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }

    @Override
    public void update(Object entity) {
        //        getSession().update(entity);
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void partUpdate(int id, String[] names, Object[] values) {
        String tab = clz.getSimpleName();
        String hql = "update " + tab + " t";
        for (int i = 0; i < names.length; i++) { hql += " set t." + names[i] + "=?"; }
        hql += " where t.id=" + id;
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) { query.setParameter(i, values[i]); }
        System.out.println("部分更新：" + hql);
        query.executeUpdate();
    }

    @Override
    public void delete(Serializable id) {
        Object obj = findById(id);
        if (obj != null) {
            getSession().delete(obj);
        }
    }

    @Override
    public T findById(Serializable id) {
        return (T) getSession().get(getClz(), id);
    }

    @Override
    public List<T> findAll() {
        return findByHQL("from "+getClz().getName());
    }

    @Override
    public List findByHQL(String hql, Object... params) {
        List<T> list = null;
        Session session = null;
        try {
            session = this.getSession();
            Query query = session.createQuery(hql);
            for (int i = 0; params != null && i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
            System.out.println("HQL查询：" + hql);
            list = query.list();
        } catch (Exception ex) {
            System.out.println("执行HQL查找对象出现错误！");
            ex.printStackTrace();
        } finally {
//            if (session != null) { session.close(); }
        }
        return list;
    }

    @Override
    public List queryPage(String hql, int pageNo, int pageSize) {
        List<T> list = null;
        Session session = null;
        try {
            session = this.getSession();
            Query query = session.createQuery(hql);
            list = query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
        } catch (Exception ex) {
            System.out.println("分页查询出现错误出现错误！");
            ex.printStackTrace();
        } finally {
//            if (session != null) { session.close(); }
        }
        return list;
    }
}
