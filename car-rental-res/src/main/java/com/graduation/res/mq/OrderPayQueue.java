package com.graduation.res.mq;

import com.graduation.common.constant.RabbitConstant;
import com.graduation.res.mq.base.RabbitMqConsumer;
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
public class OrderPayQueue extends RabbitMqConsumer{

    @RabbitHandler
    @Override
    protected void consumer(Object message) {
        logger.info("OrderPayQueue consumer message:{}",message);

    }
}
