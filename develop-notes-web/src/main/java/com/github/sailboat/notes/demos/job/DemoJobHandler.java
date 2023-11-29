package com.github.sailboat.notes.demos.job;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DemoJobHandler {

    @XxlJob("demoJobHandler")
    public ReturnT jobHandler() {

        log.info(">>>>>>>>>>>>>>  DemoJobHandler  success ");

        return ReturnT.SUCCESS;
    }

}
