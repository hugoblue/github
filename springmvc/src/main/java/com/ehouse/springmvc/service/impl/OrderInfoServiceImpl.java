package com.ehouse.springmvc.service.impl;

import com.ehouse.springmvc.dao.OrderInfoDao;
import com.ehouse.springmvc.entity.OrderInfo;
import com.ehouse.springmvc.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    public OrderInfo findMin() {
        Integer minId = orderInfoDao.findMinId();
        return findById(minId);
    }

    @Override
    public OrderInfo findById(Integer id) {
        OrderInfo oi = orderInfoDao.findById(id);
        System.out.println(oi.toString());
        return oi;
    }

    @Override
    public OrderInfo saveOrderInfo(OrderInfo entity) {
        orderInfoDao.save(entity);
        System.out.println(entity.toString());
        return entity;
    }

    @Override
    public List<OrderInfo> findAll() {
        List<OrderInfo> list = orderInfoDao.findAll();
        System.out.println(list.size());
        return list;
    }

    @Override
    public boolean saveBatch(List<OrderInfo> list) {
        int count = 0;
        for (OrderInfo info : list) {
            if (count == 15){
                String[] arr = new String[2];
                System.out.println(arr[3]);
            }
            count ++;
            orderInfoDao.save(info);
        }
        return true;
    }

    @Override
    public boolean testSaveBatch(List<OrderInfo> list) {
        int count = 0;
        for (OrderInfo info : list) {
            if (count == 15){
                String[] arr = new String[2];
                System.out.println(arr[3]);
            }
            orderInfoDao.save(info);
            count ++;
        }
        return true;
    }

}
