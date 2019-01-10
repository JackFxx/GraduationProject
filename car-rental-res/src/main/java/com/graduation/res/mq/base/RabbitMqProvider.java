package com.graduation.res.mq.base;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description rabbitmq推送
 * @author fuxiaoxiang2
 * @create 2019/01/09
 */
@Component
public class RabbitMqProvider {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqProvider.class);
    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * 推送消息至指定的队列
     * @param queueName 队列名称
     * @param message 消息内容
     */
    public void push(String queueName,Object message){
        logger.info("push message:{},to queue:{}", JSONObject.toJSONString(message),queueName);
        amqpTemplate.convertAndSend(queueName,message);
    }
}
