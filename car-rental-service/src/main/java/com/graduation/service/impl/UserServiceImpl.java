package com.graduation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.enums.UserEnum;
import com.graduation.dao.mapper.UserMapper;
import com.graduation.domain.bo.UserBO;
import com.graduation.res.redis.RedisClient;
import com.graduation.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 用户相关信息服务
 * @Author fuxiaoxiang2
 * @Create 2019/1/7 9:53
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisClient redisClient;

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 用户注册
     * @Date 9:54 2019/1/7
     * @Param [userBO]
     **/
    @Override
    public int registryUser(UserBO userBO) {
        if (null == userBO || StringUtils.isBlank(userBO.getUsername()) || StringUtils.isBlank(userBO.getPassword())) {
            logger.warn("username or password is null!");
            return UserEnum.USER_PASS_ERROR.getCode();
        }
        if (null == userBO.getGender()) {
            logger.warn("this user:{},gender is null!", JSONObject.toJSONString(userBO));
            return UserEnum.USER_GENDER_NULL.getCode();
        }
        //获取时间信息
        userBO.setCreateTime(getTime());
        userBO.setModifyTime(getTime());
        int count = -3;
        try {
            count = userMapper.insert(userBO);
        } catch (Exception e) {
            logger.error("registry user:{}", JSONObject.toJSONString(userBO));
        }
        return count;
    }

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 用户登陆专用
     * @Date 10:24 2019/1/7
     * @Param [username, password, mobile]
     **/
    @Override
    public int loginUser(String username, String password, Long mobile) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {//使用用户名和密码登陆
            UserBO user = null;
            try {
                //组装登陆信息
                UserBO bo = new UserBO();
                bo.setUsername(username);
                bo.setPassword(password);
                user = userMapper.loginUser(bo);
            } catch (Exception e) {
                logger.error("login user:{},occurs:", username, e);
            }
            if (null != user) {
                //登陆成功 信息暂存于redis
                redisClient.loginSetUser(user);
                return UserEnum.USER_SUCCESS.getCode();
            }
            return UserEnum.NO_THIS_USER.getCode();
        }
        if (null != mobile) {//TODO 使用手机登陆

        }
        return UserEnum.NO_USER_PASSWORD.getCode();
    }

    /**
     * @return
     * @Author fuxiaoxiang2
     * @Description 注销
     * @Date 11:30 2019/1/7
     * @Param [userId]
     **/
    @Override
    public void checkoutLogin(Long userId) {
        redisClient.checkOutLogin(userId);
    }

    @Override
    public int updateUser(UserBO userBO) {
        if(null == userBO || userBO.getId() ==null || userBO.getMobile() == null){
            logger.warn("please input update condition");
            return UserEnum.USER_PARAM_ERROR.getCode();
        }
        int updateCount = -5;
        try {
            updateCount = userMapper.updateUser(userBO);
        }catch (Exception e){
            logger.error("updateUser user:{},occurs:", JSONObject.toJSONString(userBO), e);
        }
        return updateCount;
    }

    /**
     * 获取unix时间戳
     *
     * @return
     */
    private Long getTime() {
        return System.currentTimeMillis();
    }
}
