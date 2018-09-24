package com.ehouse.batch.batch001.tasklet;

import com.ehouse.springmvc.entity.VersionInfo;
import com.ehouse.springmvc.service.VersionInfoService;
import com.ehouse.springmvc.utils.UUIDutil;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component("customTasklet")
public class CustomTasklet implements Tasklet {

    @Autowired
    private VersionInfoService versionInfoService;
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();

        VersionInfo versionInfo = new VersionInfo();
        versionInfo.setVersionNo(UUIDutil.getuuid());
        versionInfo.setCreateDate(new Date());

        versionInfoService.saveVersionInfo(versionInfo);

        System.out.println("######################### insert versionInfo -- " + versionInfo.toString());
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("versionInfo",
                versionInfo.getId());
        if(versionInfo.getId()%5==0){
            chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.FAILED);
        }
//        if(versionInfo.getId()!=5){
//            return RepeatStatus.CONTINUABLE;
//        }
        return RepeatStatus.FINISHED;
    }
}
