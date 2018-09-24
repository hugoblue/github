package com.ehouse.batch.batch001.process;

import com.ehouse.springmvc.entity.OrderInfo;
import com.ehouse.springmvc.entity.SaleMan;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("customItemProcessor")
public class CustomItemProcessor implements ItemProcessor<SaleMan,OrderInfo> {

    protected StepExecution stepExecution;

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public OrderInfo process(SaleMan saleMan) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(saleMan.getOrderId());
        orderInfo.setDescription("this is a OrderInfo with saleMan description -- "+saleMan.getDescription());
//        System.out.println(orderInfo.toString());
//        try{
//            if(saleMan.getId() == (204)){
//                System.out.println(orderInfo.getId().toString());
//            }
//        }catch (Exception e){
//            System.out.println("################## process exception ,and retry -- "+orderInfo.toString());
//            orderInfo.setId(saleMan.getId()+1);
//        }

//        if(OrderInfo.getId()%7==0){
//            throw new ArrayIndexOutOfBoundsException();
//        }
        return orderInfo;
    }
}
