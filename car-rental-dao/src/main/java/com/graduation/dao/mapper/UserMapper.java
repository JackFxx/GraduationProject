package com.graduation.dao.mapper;

import com.graduation.dao.base.BaseCrudMapper;
import com.graduation.dao.base.BaseQueryMapper;
import com.graduation.dao.base.ISqlMapper;
import com.graduation.domain.bo.UserBO;
import com.graduation.domain.po.UserPO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/6 19:46
 */
public interface UserMapper extends ISqlMapper, BaseCrudMapper<UserBO>,BaseQueryMapper<UserBO,UserPO>{
    /**
     * 更新用户信息
     */
    int updateUser(UserBO userBO);

    /**
     * 登陆
     */
    UserBO loginUser(UserBO userBO);

    /**
     * 使用手机号登陆
     */
    UserBO loginUserByMobile(@Param("mobile") Long mobile);
}
