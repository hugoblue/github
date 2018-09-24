package com.ehouse.batch.batch001.listener;

import com.ehouse.springmvc.entity.SaleMan;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component("readListener")
public class ReadListener implements ItemReadListener<SaleMan> {
    @Override
    public void beforeRead() {
        System.out.println("************************ before read");
    }

    @Override
    public void afterRead(SaleMan saleMan) {
        System.out.println("************************ after read");
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("************************ error read--"+e.getMessage());
    }
}
