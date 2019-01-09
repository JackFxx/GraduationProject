package com.graduation.res.redis;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.constant.RedisConstant;
import com.graduation.domain.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption redis相关基础服务
 * @Author fuxiaoxiang2
 * @Create 2019/1/7 10:41
 */
@Service("redisClient")
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
    public void loginSetUser(UserBO userBO) {
        try {
            redisTemplate.opsForValue().set(RedisConstant.USER_KEY_PRE + userBO.getId(), userBO, RedisConstant.LOGIN_KEEP_TIME, TimeUnit.HOURS);
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
     * @Author fuxiaoxiang2
     * @Description 用户注销
     * @Date 11:28 2019/1/7
     * @Param [userId]
     * @return int
     **/
    public void checkOutLogin(Long userId) {
        try {
            redisTemplate.delete(RedisConstant.USER_KEY_PRE+userId);
        }catch (Exception e){
            logger.error("checkout user:{},occurs:", userId, e);
        }
    }
}
