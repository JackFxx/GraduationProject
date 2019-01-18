package com.graduation.service.task;

import com.graduation.service.CategoryService;
import com.graduation.service.task.common.BaseSystemTaskService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 更新类目浏览量、点赞数、点踩数任务
 * @Author fuxiaoxiang2
 * @Create 2019/1/9 22:29
 */
@Component
@EnableScheduling
public class UpdateCategoryTask extends BaseSystemTaskService {

    @Resource(name = "categoryService")
    private CategoryService categoryService;
    /**
     * 定时任务 每60秒执行一次
     */
    @Scheduled(cron = "0 0 5 * * ?")
    @Override
    public void execute() {

    }
}
