package com.graduation.common.constant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption redis相关常量
 * @Author fuxiaoxiang2
 * @Create 2019/1/7 10:49
 */
public class RedisConstant {
    /**
     * 用户key
     */
    public static final String USER_KEY_PRE = "car:rental:user:";
    /**
     * 默认登陆时长
     */
    public static final Long LOGIN_KEEP_TIME = 2L;
    /**
     * 默认续约时长
     */
    public static final Long LOGIN_ADD_TIME = 1L;
}
