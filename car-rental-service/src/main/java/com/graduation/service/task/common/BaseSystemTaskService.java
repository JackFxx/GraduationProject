package com.graduation.service.task.common;

import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 系统基本任务执行接口
 * @Author fuxiaoxiang2
 * @Create 2019/1/9 22:16
 */
public abstract class BaseSystemTaskService {
    protected static final Logger logger = LoggerFactory.getLogger(BaseSystemTaskService.class);
    /**
     * 定时任务
     */
    protected abstract void execute();
}
