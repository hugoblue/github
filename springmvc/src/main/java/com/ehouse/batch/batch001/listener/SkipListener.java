package com.ehouse.batch.batch001.listener;

import com.ehouse.springmvc.entity.OrderInfo;
import com.ehouse.springmvc.entity.SaleMan;
import org.springframework.stereotype.Component;

@Component("skipListener")
public class SkipListener implements org.springframework.batch.core.SkipListener<SaleMan,OrderInfo> {
    @Override
    public void onSkipInRead(Throwable throwable) {
        System.out.println("#################### skip in read -- "+throwable.getMessage());
    }

    @Override
    public void onSkipInWrite(OrderInfo orderInfo, Throwable throwable) {
        System.out.println("#################### skip in write -- "+throwable.getMessage());
        System.out.println("#################### skip in write -- "+orderInfo.getDescription());
    }

    @Override
    public void onSkipInProcess(SaleMan saleMan, Throwable throwable) {
        System.out.println("#################### skip in write -- "+throwable.getMessage());
        System.out.println("#################### skip in write -- "+saleMan.getDescription());
    }
}
