package com.tinet.ctilink.task.job;

import com.tinet.ctilink.task.service.ReCurlService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author fengwei //
 * @date 16/5/31 15:43
 */
@Component
@DisallowConcurrentExecution
public class ReCurlJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReCurlService reCurlService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date startTime = new Date();
        if (logger.isInfoEnabled()) {
            logger.info("reCurlJob start, time:" + startTime.getTime());
        }

        //失败的重新推送
        reCurlService.reCurl();

        Date endTime = new Date();
        if (logger.isInfoEnabled()) {
            logger.info("reCurlJob end, time:" +endTime.getTime(),
                    ", " + (endTime.getTime() - startTime.getTime()) + "ms");
        }

    }
}
