package com.ehouse.springmvc.service.impl;

import com.ehouse.springmvc.dao.VersionInfoDao;
import com.ehouse.springmvc.dao.VersionInfoDao;
import com.ehouse.springmvc.entity.VersionInfo;
import com.ehouse.springmvc.entity.VersionInfo;
import com.ehouse.springmvc.service.VersionInfoService;
import com.ehouse.springmvc.service.VersionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionInfoServiceImpl implements VersionInfoService {

    @Autowired
    private VersionInfoDao versionInfoDao;

    @Override
    public VersionInfo findById(Integer id) {
        VersionInfo oi = versionInfoDao.findById(id);
        System.out.println(oi.toString());
        return oi;
    }

    @Override
    public VersionInfo saveVersionInfo(VersionInfo entity) {
        versionInfoDao.save(entity);
        System.out.println(entity.toString());
        return entity;
    }

    @Override
    public List<VersionInfo> findAll() {
        List<VersionInfo> list = versionInfoDao.findAll();
        System.out.println(list.size());
        return list;
    }

    @Override
    public boolean saveBatch(List<VersionInfo> list) {
        int count = 0;
        for (VersionInfo info : list) {
            if (count == 15){
                String[] arr = new String[2];
                System.out.println(arr[3]);
            }
            count ++;
            versionInfoDao.save(info);
        }
        return true;
    }

    @Override
    public boolean testSaveBatch(List<VersionInfo> list) {
        int count = 0;
        for (VersionInfo info : list) {
            if (count == 15){
                String[] arr = new String[2];
                System.out.println(arr[3]);
            }
            versionInfoDao.save(info);
            count ++;
        }
        return true;
    }

}
