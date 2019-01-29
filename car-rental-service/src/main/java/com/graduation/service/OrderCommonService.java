package com.graduation.service;

import com.graduation.domain.bo.OrderBO;
import com.graduation.domain.po.OrderPO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 订单通用服务 主要用于操作本地数据库
 * @Author fuxiaoxiang2
 * @Create 2019/1/28 11:25
 */
public interface OrderCommonService {
    /**
     * 新增订单
     */
    int insertOrder(OrderBO orderBO);

    /**
     * 支付
     * @param orderBO
     * @return
     */
    int payOrder(OrderBO orderBO);

    /**
     * 取消
     * @param orderBO
     * @return
     */
    int cancelOrder(OrderBO orderBO);

    /**
     * 查看
     */
    List<OrderBO> queryOrder(OrderPO orderPO);
}
