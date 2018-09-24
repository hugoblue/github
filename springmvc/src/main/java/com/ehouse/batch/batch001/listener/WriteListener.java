package com.ehouse.batch.batch001.listener;

import com.ehouse.springmvc.entity.OrderInfo;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("writeListener")
public class WriteListener implements ItemWriteListener<OrderInfo> {
    @Override
    public void beforeWrite(List<? extends OrderInfo> list) {
        System.out.println("************************ this write is "+this);
        System.out.println("************************ before write");
    }

    @Override
    public void afterWrite(List<? extends OrderInfo> list) {
        System.out.println("************************ after write! size is "+list.size());
    }

    @Override
    public void onWriteError(Exception e, List<? extends OrderInfo> list) {
        System.out.println("************************ error write! size is "+list.size());
        System.out.println("************************ error write!--"+e.getMessage());
    }
}
