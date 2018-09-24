package com.ehouse.batch.batch001.reader;

import com.ehouse.springmvc.entity.SaleMan;
import com.ehouse.springmvc.entity.VersionInfo;
import com.ehouse.springmvc.service.OrderInfoService;
import com.ehouse.springmvc.utils.UUIDutil;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

//@StepScope
@Component("customItemReader")
public class CustomItemReader extends AbstractPagingItemReader<SaleMan> {

    @Autowired
    private OrderInfoService orderInfoService;

    protected StepExecution stepExecution;

//    private int count = 0;

//    private int size = 0;

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

//    @Value("#{jobParameters[pageSize]}")
    @Value("${batch.customerJob.pageSize}")
    private Long psize;

    protected void doReadPage() {
//        System.out.println(stepExecution.getJobParameters().getLong("psize"));
//        System.out.println(stepExecution.getJobParameters().getString("p1"));
//        System.out.println(stepExecution.getJobParameters().getString("p2"));
//        System.out.println(stepExecution.getJobParameters().getString("testParam"));
//        System.out.println("######################### pageSize: " + psize);
        if (this.results == null) {
            this.results = new CopyOnWriteArrayList();
        } else {
            this.results.clear();
        }

        if(super.getPage()>=5){
            return;
        }

        //        List<OrderInfo> list = orderInfoService.findAll();
        //        System.out.println(list.size());

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
        super.setPageSize(5);
        for (int i = 0; i < 5; i++) {
            SaleMan saleMan = new SaleMan();
            saleMan.setId(100*super.getPage() + i);
            saleMan.setOrderId(UUIDutil.getuuid());
            saleMan.setDescription(" This is a saleMan ! -- " + 100*super.getPage() + i );
            this.results.add(saleMan);
        }

//        System.out.println("#####################" + size);

        stepExecution.getExecutionContext().put("stepVersionNo","versionNo"+ super.getPage());

    }

    @Override
    protected void doJumpToPage(int i) {

    }

}
