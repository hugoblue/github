package com.ehouse.springmvc.service;

import com.ehouse.springmvc.entity.OrderInfo;

import java.util.List;

public interface OrderInfoService {

    OrderInfo findMin();

    OrderInfo findById(Integer id);

    OrderInfo saveOrderInfo(OrderInfo entity);

    List<OrderInfo> findAll();

    boolean saveBatch(List<OrderInfo> list);

    boolean testSaveBatch(List<OrderInfo> list);

}
