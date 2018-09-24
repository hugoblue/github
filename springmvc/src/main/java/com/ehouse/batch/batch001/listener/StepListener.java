package com.ehouse.batch.batch001.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component("stepListener")
public class StepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("************************ before step--"+stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("************************ after step--" + stepExecution.getStepName());
        System.out.println("************************ after step--" + stepExecution.getExitStatus()
                .getExitCode());
//        System.out.println("************************ after step--" + stepExecution.getExitStatus()
//                .getExitDescription());
        return stepExecution.getExitStatus();
    }
}
