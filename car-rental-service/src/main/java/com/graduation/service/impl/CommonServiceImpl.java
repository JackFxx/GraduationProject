package com.graduation.service.impl;

import com.graduation.common.util.PictureUtil;
import com.graduation.res.redis.RedisClient;
import com.graduation.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/18 9:47
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Resource(name = "redisClient")
    private RedisClient redisClient;

    /**
     * @return
     * @Author fuxiaoxiang2
     * @Description 拉取首页banner图
     * @Date 9:47 2019/1/18
     * @Param
     **/
    @Override
    public List<String> pullHomeBanner() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return redisClient.pullHomeBannerUrl(dayOfTheWeek);
    }

    /**
     * @return
     * @Author fuxiaoxiang2
     * @Description 存储banner图
     * @Date 9:48 2019/1/18
     * @Param
     **/
    @Override
    public void pushBanner(MultipartFile file) {
        MultipartFile[] files = {file};
        List<String> bannerUrlList = PictureUtil.parseAndSaveBanner(files);
        redisClient.saveBannerUrl(bannerUrlList);
    }
}
