package com.graduation.res.redis;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.constant.CommonConstant;
import com.graduation.common.constant.RedisConstant;
import com.graduation.domain.bo.UserBO;
import com.graduation.domain.vo.CategoryVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption redis相关基础服务
 * @Author fuxiaoxiang2
 * @Create 2019/1/7 10:41
 */
@Component
public class RedisClient {

    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @return void
     * @Author fuxiaoxiang2
     * @Description 登陆信息暂存redis 减少db压力
     * @Date 10:46 2019/1/7
     * @Param [userBO]
     **/
    public void loginSetUserToken(UserBO userBO, String token) {
        try {
            redisTemplate.opsForValue().set(RedisConstant.USER_KEY_PRE + userBO.getId(), userBO, RedisConstant.LOGIN_KEEP_TIME, TimeUnit.HOURS);
            redisTemplate.opsForValue().set(RedisConstant.USER_TOKEN_PRE + token, userBO, RedisConstant.LOGIN_KEEP_TIME, TimeUnit.HOURS);
        } catch (Exception e) {
            logger.error("save user:{},occurs:", JSONObject.toJSONString(userBO), e);
        }
    }

    /**
     * @return void
     * @Author fuxiaoxiang2
     * @Description 续约登陆时间
     * @Date 11:08 2019/1/7
     * @Param [userId]
     **/
    public int renewalLoginTime(Long userId) {
        try {
            UserBO user = (UserBO) redisTemplate.opsForValue().get(RedisConstant.USER_KEY_PRE + userId);
            if (!StringUtils.isBlank(user.getUsername())) {//续约
                redisTemplate.opsForValue().set(RedisConstant.USER_KEY_PRE + user.getId(), user, RedisConstant.LOGIN_ADD_TIME, TimeUnit.HOURS);
                return 1;
            } else {//身份过期
                return -1;
            }
        } catch (Exception e) {
            logger.error("renewal user:{},occurs:", userId, e);
        }
        return 0;
    }

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 用户注销
     * @Date 11:28 2019/1/7
     * @Param [userId]
     **/
    public void checkOutLogin(Long userId) {
        try {
            redisTemplate.delete(RedisConstant.USER_KEY_PRE + userId);
        } catch (Exception e) {
            logger.error("checkout user:{},occurs:", userId, e);
        }
    }

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 更新并返回单个品目的浏览量
     * @Date 20:07 2019/1/9
     * @Param [categoryId, flag] flag:0更新浏览量 1更新点赞 2更新点踩
     **/
    public long incAndGetNum(Long categoryId, int flag) {
        long num = 0;
        try {
            switch (flag) {
                case 0:
                    num = redisTemplate.opsForValue().increment(RedisConstant.CATEGORY_VIEW_PRE + categoryId, 1);
                case 1:
                    num = redisTemplate.opsForValue().increment(RedisConstant.CATEGORY_LIKE_PRE + categoryId, 1);
                case 2:
                    num = redisTemplate.opsForValue().increment(RedisConstant.CATEGORY_HATE_PRE + categoryId, 1);
                    break;
                default:
                    logger.warn("please input right flag in category:{}", categoryId);
                    break;
            }
        } catch (Exception e) {
            logger.error("inc category:{},viewNum", categoryId, e);
        }
        return num;
    }

    /**
     * @return void
     * @Author fuxiaoxiang2
     * @Description 类目排行榜，由于热度较高，浏览量也比较大，存储于redis中，方便处理
     * @Date 15:52 2019/1/17
     * @Param [vo, score, yesterday, today, count] count ==0 表示已排完
     **/
    public void updateCategoryRank(CategoryVO vo, Double score, String yesterday, String today, int count) {
        if (null == vo) {
            logger.warn("no any datas");
            return;
        }
        try {
            redisTemplate.opsForZSet().add(RedisConstant.CATEGORY_RANK_PRE + today, vo, score);
            //更新完以后，删除昨天的排行榜
            if (count == 0) {
                redisTemplate.delete(RedisConstant.CATEGORY_RANK_PRE + yesterday);
            }
        } catch (Exception e) {
            logger.error("updateCategoryRank error,", e);
        }
    }

    /**
     * @return
     * @Author fuxiaoxiang2
     * @Description 存储banner图
     * @Date 18:36 2019/1/17
     * @Param [urlList]
     **/
    public void saveBannerUrl(List<String> urlList) {
        if (CollectionUtils.isEmpty(urlList)) {
            return;
        }
        try {
            urlList.forEach(url -> redisTemplate.opsForList().leftPush(RedisConstant.BANNER_URL_PRE, url));
        } catch (Exception e) {
            logger.error("save bannerUrl", e);
        }
    }

    public List<String> pullHomeBannerUrl(int dayOfTheWeek) {
        //每天取不同的banner图
        List<String> bannerList = new ArrayList<>();
        int count = 21;
        while (count > 0) {
            String bannerUrl = (String) redisTemplate.opsForList().rightPopAndLeftPush(RedisConstant.BANNER_URL_PRE, RedisConstant.BANNER_URL_PRE);
            if (count % dayOfTheWeek == 0 && bannerList.size() < CommonConstant.HOME_BANNER_SIZE) {
                bannerList.add(bannerUrl);
            }
            count--;
        }
        return bannerList;
    }

    public static void main(String[] args) {

    }
}
