package com.graduation.service.task;

import com.graduation.domain.bo.CategoryBO;
import com.graduation.domain.po.CategoryPO;
import com.graduation.domain.vo.CategoryVO;
import com.graduation.res.redis.RedisClient;
import com.graduation.service.CategoryService;
import com.graduation.service.task.common.BaseSystemTaskService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 类目热销排行榜任务
 * @Author fuxiaoxiang2
 * @Create 2019/1/10 14:24
 */
@Component
@EnableScheduling
public class RankCategoryTask extends BaseSystemTaskService {
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "redisClient")
    private RedisClient redisClient;

    /**
     * 明天凌晨5点执行
     */
    @Scheduled(cron = "0 0 5 * * ?")
    @Override
    public void execute() {
        logger.info("RankCategoryTask task is executing");
        this.handler();
        logger.info("RankCategoryTask task is over");
    }

    private void handler() {
        CategoryPO po = new CategoryPO();
        String yesterday = new DateTime().minusHours(6).toString("dd");
        String today = new DateTime().minusHours(0).toString("dd");
        int count = 1;
        int pageNo = 1;
        int pageSize = 100;
        List<CategoryVO> categoryList = new ArrayList<>();
        while (count >0){
            po.setPageNo(pageNo);
            po.setPageSize(pageSize);
            List<CategoryVO> categoryVOList = categoryService.listCategory(po);
            if (CollectionUtils.isEmpty(categoryList)) {
                return;
            }
            count = categoryVOList.size();
            pageNo++;
            categoryVOList.forEach(vo->{
                if(null == vo){
                    return;
                }
                redisClient.updateCategoryRank(vo,getScore(vo.getViewNum(),vo.getLikeNum(),vo.getHateNum()),yesterday,today,1);
            });
        }
        redisClient.updateCategoryRank(null,null,yesterday,today,0);
    }
    /**
     * 类目热度分值计算公式
     *
     * @param viewNum 3/10
     * @param likeNum 8/10
     * @param hateNum -1/10
     * @return
     */
    private Double getScore(int viewNum, int likeNum, int hateNum) {
        return (double) (viewNum * 3 + likeNum * 8 + hateNum * (-1));
    }
}
