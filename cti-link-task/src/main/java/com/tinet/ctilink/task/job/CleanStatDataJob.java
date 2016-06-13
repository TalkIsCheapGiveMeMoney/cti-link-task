package com.tinet.ctilink.task.job;

import com.tinet.ctilink.cache.CacheKey;
import com.tinet.ctilink.cache.RedisService;
import com.tinet.ctilink.inc.Const;
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
 * @date 16/5/31 10:09
 */
@Component
@DisallowConcurrentExecution
public class CleanStatDataJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisService redisService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date startTime = new Date();
        if (logger.isInfoEnabled()) {
            logger.info("cleanStatDataJob start, time:" + startTime.getTime());
        }
        //清理统计值

        //tts统计
        redisService.set(Const.REDIS_DB_NON_CONFIGURE_INDEX, CacheKey.TTS_SUCCESS_COUNT, 0);
        redisService.set(Const.REDIS_DB_NON_CONFIGURE_INDEX, CacheKey.TTS_FAIL_COUNT, 0);

        //realtime统计
        redisService.set(Const.REDIS_DB_NON_CONFIGURE_INDEX, CacheKey.TTS_SUCCESS_COUNT, 0);
        redisService.set(Const.REDIS_DB_NON_CONFIGURE_INDEX, CacheKey.TTS_FAIL_COUNT, 0);

        //curl
        redisService.set(Const.REDIS_DB_NON_CONFIGURE_INDEX, CacheKey.TTS_SUCCESS_COUNT, 0);
        redisService.set(Const.REDIS_DB_NON_CONFIGURE_INDEX, CacheKey.TTS_FAIL_COUNT, 0);

        Date endTime = new Date();
        if (logger.isInfoEnabled()) {
            logger.info("cleanStatDataJob end, time:" +endTime.getTime(),
                    ", " + (endTime.getTime() - startTime.getTime()) + "ms");
        }
    }
}
