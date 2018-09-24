package com.ehouse.springmvc.dao.impl;

import com.ehouse.springmvc.dao.SaleManDao;
import com.ehouse.springmvc.entity.SaleMan;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleManDaoImpl extends BaseDaoImpl<SaleMan> implements SaleManDao {
    @Override
    public Integer findMinId() {
        String hql = " select id from "+getClz().getName() + " order by id asc ";
        Query query = getSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List list = query.list();
        if(list!=null && list.size()>0){
            return (Integer) list.get(0);
        }

        return null;
    }
}
