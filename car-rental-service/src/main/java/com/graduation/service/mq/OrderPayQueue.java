package com.graduation.service.mq;

import com.graduation.common.constant.RabbitConstant;
import com.graduation.domain.bo.OrderBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/10 10:21
 */
@Component
@RabbitListener(queues = RabbitConstant.PAY_ORDER_QUEUE)
public class OrderPayQueue extends AbstractConsumerMQ {
    private static final Logger logger = LoggerFactory.getLogger(OrderSubmitQueue.class);

    @RabbitHandler
    @Override
    protected void consumer(Object message) {
        logger.info("OrderPay queue consumer message:{}", message);
        OrderBO orderBO = parseOrder(message);
        try {
            int count = orderCommonService.payOrder(orderBO);
            if(count != 1){

            }
        }catch (Exception e){

        }
    }
}
