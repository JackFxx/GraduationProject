package com.graduation.web.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 添加该注解标识必须登陆
 * @Author fuxiaoxiang2
 * @Create 2019/1/28 15:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckLogin {
}
