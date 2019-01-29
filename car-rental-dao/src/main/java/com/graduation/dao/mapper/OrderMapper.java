package com.graduation.dao.mapper;

import com.graduation.dao.base.BaseCrudMapper;
import com.graduation.dao.base.BaseQueryMapper;
import com.graduation.domain.bo.OrderBO;
import com.graduation.domain.po.OrderPO;

import java.util.List;

public interface OrderMapper extends BaseCrudMapper<OrderBO>, BaseQueryMapper<OrderBO, OrderPO> {
    /**
     * 下单
     *
     * @param orderBO
     * @return
     */
    int saveOrder(OrderBO orderBO);

    /**
     * 支付订单
     *
     * @param orderBO
     * @return
     */
    int payOrder(OrderBO orderBO);

    /**
     * 取消订单
     */
    int cancelOrder(OrderBO orderBO);

    /**
     * 记录支付时间
     * @param orderBO
     * @return
     */
    int recordPayTime(OrderBO orderBO);

    /**
     * 取消订单
     * @param orderPO
     * @return
     */
    List<OrderBO> queryOrder(OrderPO orderPO);
}
