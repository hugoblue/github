package com.ehouse.batch.batch001.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("customConfig")
public class CustomConfig {

    @Value("${batch.customerJob.pageSize}")
    private Long psize;

    @Value("${batch.customerJob.param1}")
    private String p1;

    @Value("${batch.customerJob.param2}")
    private String p2;

    public Long getPsize() {
        return psize;
    }

    public void setPsize(Long psize) {
        this.psize = psize;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }
}
