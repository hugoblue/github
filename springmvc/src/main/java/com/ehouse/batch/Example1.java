package com.ehouse.batch;

import com.ehouse.batch.batch001.configuration.CustomConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.support.ClassPathXmlApplicationContextFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobBuilderHelper;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.job.JobStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * User: Jean-Philippe Briend
 * Date: 5/12/11
 * Time: 10:28 PM
 */
public class Example1 {

    public static void main(String[] args) {
        String jobName = "customJob";
        String[] xmlPaths = {"classpath:/spring/applicationContext.xml", "classpath:/batch/batch-" + jobName + ".xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlPaths);

        CustomConfig customConfig = (CustomConfig) context.getBean("customConfig");

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

        Job job = (Job) context.getBean("customJob");

        System.out.println(job.isRestartable());

        JobParameters jobParameters1 = new JobParametersBuilder().addLong("psize", customConfig.getPsize())
                .addString("p1", customConfig.getP1()).addString("p2", customConfig.getP2())
                .addString("testParam", "aaaaa").toJobParameters();

        JobParameters jobParameters2 = new JobParametersBuilder().addLong("psize", customConfig.getPsize())
                .addString("p1", customConfig.getP1()).addString("p2", customConfig.getP2())
                .addString("testParam", "aaaaa").toJobParameters();

        try {
            jobLauncher.run(job, jobParameters1);
//            jobLauncher.run(job, jobParameters1);
//            JobParameters jobParameters3 = job.getJobParametersIncrementer().getNext(jobParameters1);
//            jobLauncher.run(job, jobParameters1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
