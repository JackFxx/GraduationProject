package com.graduation.service;

import com.graduation.domain.bo.OrderBO;
import com.graduation.domain.po.OrderPO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/27 20:49
 */
public interface OrderService {

    /**
     * 提单
     * @param orderBO
     * @return
     */
    int submitOrder(OrderBO orderBO);

    /**
     * 支付
     * @param orderBO
     * @return
     */
    int payOrder(OrderBO orderBO);

    /**
     * 取消
     */
    int cancelOrder(OrderBO orderBO);

    /**
     * 查看
     */
    List<OrderBO> queryOrder(OrderPO orderPO);
}
