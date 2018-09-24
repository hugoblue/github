package com.ehouse.batch.batch001.writer;

import com.ehouse.springmvc.entity.OrderInfo;
import com.ehouse.springmvc.service.OrderInfoService;
import com.ehouse.springmvc.utils.UUIDutil;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("customItemWriter")
public class CustomItemWriter implements ItemWriter<OrderInfo> {

    protected StepExecution stepExecution;

    @Autowired
    private OrderInfoService orderInfoService;

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public void write(List<? extends OrderInfo> list1) throws Exception {
        stepExecution.getExecutionContext().get("stepVersionNo");
//        for (OrderInfo orderInfo : list1) {
//            System.out.println(orderInfo.toString());
////            if (student.getId()>52 && student.getId()<55){
////                throw new FileNotFoundException();
////            }
//        }

        Integer versionInfo = null;
        try {
            versionInfo = (Integer) stepExecution.getJobExecution().getExecutionContext().get("versionInfo");
        } catch (Exception e) {
            System.out.println("error in customerItemReader : can not get versionInfo from Job ExecutionContext !");
            e.printStackTrace();
        }
        if(versionInfo!=null){
            System.out.println(versionInfo.toString());
        }
        try {
            //saveBatch
            List<OrderInfo> list = new ArrayList<>();
            for (int i = 0 ; i < 100; i++) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId("No."+i);
                orderInfo.setDescription("This a orderInfo by auto generator!--" + UUIDutil.getuuid());
                orderInfo.setVersionNo(versionInfo.toString());

                list.add(orderInfo);
            }
            orderInfoService.saveBatch(list);
        } catch (Exception e) {
            //            e.printStackTrace();
            System.out.println("***************************** has transaction");
            List<OrderInfo> exists = orderInfoService.findAll();
            System.out.println(exists.size());
        }


        try {
            //saveBatch
            List<OrderInfo> list = new ArrayList<>();
            for (int i = 0 ; i < 100; i++) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId("No."+i);
                orderInfo.setDescription("no transaction!--" + UUIDutil.getuuid());
                orderInfo.setVersionNo(versionInfo.toString());

                list.add(orderInfo);
            }
            orderInfoService.testSaveBatch(list);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("######################## no transaction !");
            List<OrderInfo> exists = orderInfoService.findAll();
            System.out.println(exists.size());
        }


    }
}
