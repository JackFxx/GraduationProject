package com.graduation.service.mq;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.constant.RabbitConstant;
import com.graduation.domain.bo.OrderBO;
import com.graduation.service.OrderCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 提单队列
 * @Author fuxiaoxiang2
 * @Create 2019/1/27 20:54
 */
@Component
@RabbitListener(queues = RabbitConstant.SUBMIT_ORDER_QUEUE)
public class OrderSubmitQueue extends AbstractConsumerMQ {

    @RabbitHandler
    @Override
    protected void consumer(Object message) {
        logger.info("submit queue receive message:{}",JSONObject.toJSONString(message));
        //提取数据
        OrderBO orderBO = parseOrder(message);
        try {
            int count = orderCommonService.insertOrder(orderBO);
            if (count != 1) {
                logger.info("submit order:{},failed", JSONObject.toJSONString(orderBO));
            }
        } catch (Exception e) {
            logger.error("submit order error:{},", JSONObject.toJSONString(orderBO), e);
        }
    }
}
