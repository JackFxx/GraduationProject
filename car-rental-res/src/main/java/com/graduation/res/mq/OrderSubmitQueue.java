package com.graduation.res.mq;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.constant.RabbitConstant;
import com.graduation.dao.mapper.OrderMapper;
import com.graduation.domain.bo.OrderBO;
import com.graduation.res.mq.base.RabbitMqConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 提单队列
 * @Author fuxiaoxiang2
 * @Create 2019/1/27 20:54
 */
@Component
@RabbitListener(queues = RabbitConstant.SUBMIT_ORDER_QUEUE)
public class OrderSubmitQueue extends RabbitMqConsumer{

    @Autowired
    OrderMapper orderMapper;
    @RabbitHandler
    @Override
    protected void consumer(Object message) {
        logger.info("receive submit order:{}", JSONObject.toJSONString(message));
        if(message instanceof OrderBO){
            OrderBO orderBO = (OrderBO)message;
            try {
                orderMapper.saveOrder(orderBO);
            }catch (Exception e){
                logger.error("submit order error:{},",JSONObject.toJSONString(orderBO),e);
            }
        }else {
            logger.warn("submit order failed,order:{}",JSONObject.toJSONString(message));
        }
    }
}
