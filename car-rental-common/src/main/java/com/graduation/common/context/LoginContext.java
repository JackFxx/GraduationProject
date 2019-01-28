package com.graduation.common.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 将登陆信息存储于ThreadLocal中
 * @Author fuxiaoxiang2
 * @Create 2019/1/28 11:59
 */
@Slf4j
public class LoginContext {
    private static final ThreadLocal<Map<String, Object>> THREAD_MAP = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };
    /**
     * @return T
     * @Author fuxiaoxiang2
     * @Description 取信息
     * @Date 12:03 2019/1/28
     * @Param [key]
     **/
    public static <T> T get(String key) {
        log.info("thread:{}",Thread.currentThread());
        log.info("get user message,user:{}", JSONObject.toJSONString(key));
        Map<String, Object> map = THREAD_MAP.get();
        print();
        return (T) map.get(key);
    }

    /**
     * @return T
     * @Author fuxiaoxiang2
     * @Description 存信息
     * @Date 12:03 2019/1/28
     * @Param [key, value]
     **/
    public static void set(String key, Object value) {
        log.info("thread:{}",Thread.currentThread());
        log.info("save user message,user:{}", JSONObject.toJSONString(value));
        Map<String, Object> map = THREAD_MAP.get();
        map.put(key, value);
        print();
    }

    public static void print() {
        Map<String, Object> map = THREAD_MAP.get();
        if (CollectionUtils.isEmpty(map)) {
            log.error("map is null");
            return;
        }
        for (Map.Entry<String, Object> m : map.entrySet()) {
            log.info("map:{},", JSON.toJSONString(m));
        }
    }
}
