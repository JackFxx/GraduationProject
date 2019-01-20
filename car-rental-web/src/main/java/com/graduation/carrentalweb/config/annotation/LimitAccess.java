package com.graduation.carrentalweb.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 限流注解
 * @Author fuxiaoxiang2
 * @Create 2019/1/18 12:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
public @interface LimitAccess {
    /**
     * 时间 毫秒
     * @return
     */
    int time();

    /**
     * 次数
     * @return
     */
    int count();
}
