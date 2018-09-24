package com.ehouse.batch.batch001.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component("jobListener")
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("************************ before job");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("************************ after chunk");
    }
}
