package com.graduation.service.task.config;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/**
 * @author fuxiaoxiang2
 * @description 为多个定时任务分配多个线程执行，避免单线程情况下，其他任务的执行,而导致当前任务的延迟
 * @create 2018/12/29
 */
@Component
public class ScheduleConfig implements SchedulingConfigurer {
    /**
     * 配置多个线程
     *
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //配置10个线程
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}
