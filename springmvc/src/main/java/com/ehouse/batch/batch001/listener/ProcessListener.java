package com.ehouse.batch.batch001.listener;

import com.ehouse.springmvc.entity.OrderInfo;
import com.ehouse.springmvc.entity.SaleMan;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component("processListener")
public class ProcessListener implements ItemProcessListener<SaleMan,OrderInfo>{

    @Override
    public void beforeProcess(SaleMan saleMan) {
        System.out.println("************************ before process");
    }

    @Override
    public void afterProcess(SaleMan saleMan, OrderInfo orderInfo) {
        System.out.println("************************ after process");
    }

    @Override
    public void onProcessError(SaleMan saleMan, Exception e) {
        System.out.println("************************ error process");
    }
}
