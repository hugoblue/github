package com.ehouse.springmvc.dao.impl;

import com.ehouse.springmvc.dao.OrderInfoDao;
import com.ehouse.springmvc.entity.OrderInfo;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDao{

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
