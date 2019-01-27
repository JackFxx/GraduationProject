package com.graduation.domain.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 订单实体
 * @Author fuxiaoxiang2
 * @Create 2019/1/27 20:17
 */
@Data
public class OrderBO implements Serializable{
    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 类目ID
     */
    private Long categoryId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 电话
     */
    private Long mobile;
    /**
     * 商品数量
     */
    private Integer num;
    /**
     * 价格
     */
    private Double totalPrice;
    /**
     * 订单状态 0待支付 1已支付 2 主动取消 3自动取消
     */
    private Integer orderStatus;
    /**
     * 收货地址
     */
    private String receiveAddr;
    /**
     * 下单时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long modifyTime;
    /**
     * 支付时间
     */
    private Long payTime;
    /**
     * 取消时间
     */
    private Long cancelTime;
}
