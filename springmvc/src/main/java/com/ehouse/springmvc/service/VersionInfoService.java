package com.ehouse.springmvc.service;

import com.ehouse.springmvc.entity.VersionInfo;

import java.util.List;

public interface VersionInfoService {

//    VersionInfo findMin();

    VersionInfo findById(Integer id);

    VersionInfo saveVersionInfo(VersionInfo entity);

    List<VersionInfo> findAll();

    boolean saveBatch(List<VersionInfo> list);

    boolean testSaveBatch(List<VersionInfo> list);
}
