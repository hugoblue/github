package com.ehouse.springmvc.service;

import com.ehouse.springmvc.entity.OrderInfo;
import com.ehouse.springmvc.entity.SaleMan;

import java.util.List;

public interface SaleManService {


    SaleMan findMin();

    SaleMan findById(Integer id);

    SaleMan saveSaleMan(SaleMan entity);

    List<SaleMan> findAll();

    boolean saveBatch(List<SaleMan> list);

    boolean testSaveBatch(List<SaleMan> list);

}
