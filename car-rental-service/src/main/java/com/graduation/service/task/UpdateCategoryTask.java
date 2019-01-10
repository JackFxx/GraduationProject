package com.graduation.service.task;

import com.graduation.service.task.common.BaseSystemTaskService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 更新类目浏览量、点赞数、点踩数任务
 * @Author fuxiaoxiang2
 * @Create 2019/1/9 22:29
 */
@Component("updateCategoryTask")
@EnableScheduling
public class UpdateCategoryTask implements BaseSystemTaskService {

    /**
     * 定时任务 每60秒执行一次
     */
    @Scheduled(cron = "0/60 * * * * ?")
    @Override
    public void execute() {

    }
    private void handle(){

    }
}
