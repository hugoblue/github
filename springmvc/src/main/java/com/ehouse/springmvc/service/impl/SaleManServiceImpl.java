package com.ehouse.springmvc.service.impl;

import com.ehouse.springmvc.dao.SaleManDao;
import com.ehouse.springmvc.entity.SaleMan;
import com.ehouse.springmvc.service.SaleManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleManServiceImpl implements SaleManService {

    @Autowired
    private SaleManDao saleManDao;

    @Override
    public SaleMan findMin() {
        Integer minId = saleManDao.findMinId();
        return findById(minId);
    }

    @Override
    public SaleMan findById(Integer id) {
        SaleMan oi = saleManDao.findById(id);
        System.out.println(oi.toString());
        return oi;
    }

    @Override
    public SaleMan saveSaleMan(SaleMan entity) {
        saleManDao.save(entity);
        System.out.println(entity.toString());
        return entity;
    }

    @Override
    public List<SaleMan> findAll() {
        List<SaleMan> list = saleManDao.findAll();
        System.out.println(list.size());
        return list;
    }

    @Override
    public boolean saveBatch(List<SaleMan> list) {
        int count = 0;
        for (SaleMan info : list) {
            if (count == 15){
                String[] arr = new String[2];
                System.out.println(arr[3]);
            }
            count ++;
            saleManDao.save(info);
        }
        return true;
    }

    @Override
    public boolean testSaveBatch(List<SaleMan> list) {
        int count = 0;
        for (SaleMan info : list) {
            if (count == 15){
                String[] arr = new String[2];
                System.out.println(arr[3]);
            }
            saleManDao.save(info);
            count ++;
        }
        return true;
    }

}
