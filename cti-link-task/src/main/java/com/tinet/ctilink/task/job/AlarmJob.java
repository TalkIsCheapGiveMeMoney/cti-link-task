package com.tinet.ctilink.task.job;

import com.tinet.ctilink.cache.RedisService;
import com.tinet.ctilink.util.ContextUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author fengwei //
 * @date 16/6/1 20:52
 */
@DisallowConcurrentExecution
public class AlarmJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RedisService redisService = ContextUtil.getBean(RedisService.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        Date startTime = new Date();
        if (logger.isInfoEnabled()) {
            logger.info("alarmJob start, time:" + startTime.getTime());
        }

        System.out.println(redisService.time());

        Date endTime = new Date();
        if (logger.isInfoEnabled()) {
            logger.info("alarmJob end, time:" +endTime.getTime(),
                    ", " + (endTime.getTime() - startTime.getTime()) + "ms");
        }
    }
}
