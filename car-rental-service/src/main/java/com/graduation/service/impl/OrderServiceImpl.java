package com.graduation.service.impl;

import com.graduation.common.constant.RabbitConstant;
import com.graduation.common.context.LoginContext;
import com.graduation.domain.bo.OrderBO;
import com.graduation.domain.bo.UserBO;
import com.graduation.res.mq.base.RabbitMqProvider;
import com.graduation.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 订单服务具体实现
 * @Author fuxiaoxiang2
 * @Create 2019/1/27 20:51
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private RabbitMqProvider rabbitMqProvider;

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description
     * @Date 20:52 2019/1/27
     * @Param [orderBO]
     **/
    @Override
    public int submitOrder(OrderBO orderBO) {
        if (null == orderBO) {
            LOG.warn("please input orderMessage");
            return -1;
        }
        try {
            //从登陆环境取户基本信息
            UserBO user = LoginContext.get("user");
            if (null == user) {
                LOG.warn("no login user");
                return -1;
            }
            orderBO.setUserId(user.getId());
            orderBO.setUsername(user.getUsername());
            rabbitMqProvider.push(RabbitConstant.SUBMIT_ORDER_QUEUE, orderBO);
        } catch (Exception e) {
            LOG.error("submit order error", e);
            return -1;
        }
        return 1;
    }

    @Override
    public int payOrder(OrderBO orderBO) {
        return 0;
    }

    @Override
    public int cancelOrder(OrderBO orderBO) {
        return 0;
    }
}
