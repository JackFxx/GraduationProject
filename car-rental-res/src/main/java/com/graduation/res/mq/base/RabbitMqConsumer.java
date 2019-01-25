package com.graduation.res.mq.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 消费者统一服务 纯粹为了好看
 * @Author fuxiaoxiang2
 * @Create 2019/1/10 10:24
 */
public abstract class RabbitMqConsumer {
    protected static final Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);

    /**
     * 消费
     * @param message
     */
    protected abstract void consumer(Object message);
}
