package com.graduation.service;

import com.graduation.domain.bo.UserBO;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 用户登陆注册服务
 * @Author fuxiaoxiang2
 * @Create 2019/1/7 9:26
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param userBO
     * @return
     */
    int registryUser(UserBO userBO);

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @param mobile
     * @return
     */
    int loginUser(String username, String password, Long mobile);

    /**
     * 用户注销
     */
    void checkoutLogin(Long userId);

    /**
     * 更新用户信息
     *
     * @param userBO
     * @return
     */
    int updateUser(UserBO userBO);
}
