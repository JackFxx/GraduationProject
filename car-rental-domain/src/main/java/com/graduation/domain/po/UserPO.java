package com.graduation.domain.po;


import com.graduation.domain.base.BaseQueryParam;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 用户查询参数
 * @Author fuxiaoxiang2
 * @Create 2019/1/6 20:05
 */
public class UserPO extends BaseQueryParam {
    /**
     * 唯一ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别 0男 1女
     */
    private Integer gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 省份ID
     */
    private Integer provinceId;
    /**
     * 省份名
     */
    private String provinceName;
    /**
     * 城市ID
     */
    private Integer cityId;
    /**
     * 城市名
     */
    private String cityName;
    /**
     * 区ID
     */
    private Integer districtId;
    /**
     * 区名称
     */
    private String districtName;
    /**
     * 创建时间，使用unix时间戳存储
     */
    private Long createTime;
    /**
     * 修改时间
     */
    private Long modifyTime;
}
