package com.graduation.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fuxiaoxiang2
 * @description 系统的定时任务
 * @create 2019/01/09
 */
@Component
@EnableScheduling
public class ScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    /**
     * 每隔60s执行一次
     */
    @Scheduled(cron = "0/60 * * * * ?")
    public void executeTask() {
        logger.info("this is my task1");
    }

    /**
     * 每隔5秒执行一次
     */
   @Scheduled(cron = "0 0 */1 * * ?")
    public void executeTask2() throws InterruptedException {
        Thread.sleep(10000);
        logger.info("this is my task2");
    }
}