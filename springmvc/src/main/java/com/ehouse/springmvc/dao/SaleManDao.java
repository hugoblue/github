package com.ehouse.springmvc.dao;

import com.ehouse.springmvc.entity.SaleMan;

public interface SaleManDao extends BaseDao<SaleMan>{

    Integer findMinId();
}
