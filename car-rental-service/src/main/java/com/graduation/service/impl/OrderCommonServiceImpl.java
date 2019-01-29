package com.graduation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.graduation.dao.mapper.OrderMapper;
import com.graduation.domain.bo.OrderBO;
import com.graduation.domain.po.OrderPO;
import com.graduation.service.OrderCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/28 11:26
 */
@Service("orderCommonService")
public class OrderCommonServiceImpl implements OrderCommonService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderCommonServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 订单入库
     * @Date 11:28 2019/1/28
     * @Param [orderBO]
     **/
    @Override
    public int insertOrder(OrderBO orderBO) {
        if (null == orderBO) {
            LOG.warn("please input order");
            return -1;
        }
        int count = -1;
        try {
            count = orderMapper.saveOrder(orderBO);
        } catch (Exception e) {
            LOG.error("insertOrder:{},error", JSONObject.toJSONString(orderBO));
            return count;
        }
        return count;
    }

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 支付
     * @Date 10:05 2019/1/29
     * @Param [orderBO]
     **/
    @Override
    public int payOrder(OrderBO orderBO) {
        if (null == orderBO) {
            LOG.warn("please input order");
            return -1;
        }
        try {
            int count = orderMapper.payOrder(orderBO);
            if (count != 1) {//消息乱序或重复支付
                int countOne = orderMapper.recordPayTime(orderBO);
                if (countOne != 1) {//消息乱序
                    LOG.warn("order:{},cancel before pay", JSONObject.toJSONString(orderBO));
                    return -1;
                } else {//重复支付
                    LOG.warn("order:{},pay repeat", JSONObject.toJSONString(orderBO));
                    return -1;
                }
            }
        } catch (Exception e) {
            LOG.error("payOrder:{},error", JSONObject.toJSONString(orderBO), e);
        }
        return 1;
    }

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 取消
     * @Date 10:05 2019/1/29
     * @Param [orderBO]
     **/
    @Override
    public int cancelOrder(OrderBO orderBO) {
        if (null == orderBO) {
            LOG.warn("please input order");
            return -1;
        }
        try {
            int countOne = orderMapper.cancelOrder(orderBO);
            if (countOne != 1) {
                LOG.warn("cancelOrder:{},repeatable", JSONObject.toJSONString(orderBO));
                return -1;
            }
        } catch (Exception e) {

        }
        return 1;
    }

    @Override
    public List<OrderBO> queryOrder(OrderPO orderPO) {
        return null;
    }
}
