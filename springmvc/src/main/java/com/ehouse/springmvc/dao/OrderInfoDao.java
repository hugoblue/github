package com.ehouse.springmvc.dao;

import com.ehouse.springmvc.entity.OrderInfo;

import java.io.Serializable;

public interface OrderInfoDao extends BaseDao<OrderInfo> {

    Integer findMinId();
}
