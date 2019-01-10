package com.graduation.res.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description rabbitmq消费端
 * @author fuxiaoxiang2
 * @create 2019/01/09
 */
@Component
@RabbitListener(queues = "")
public class RabbitMqConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @RabbitHandler
    public void consumer(Object message){
        logger.info("consumer message:{},in queue:{}", message,"");
        Message body = (Message) message;
        byte[] content = body.getBody();
    }
}
