package com.graduation.service.mq;

import com.alibaba.fastjson.JSONObject;
import com.graduation.domain.bo.OrderBO;
import com.graduation.service.OrderCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 消费者统一父类
 * @Author fuxiaoxiang2
 * @Create 2019/1/29 9:57
 */
public abstract class AbstractConsumerMQ {
    protected static final Logger logger = LoggerFactory.getLogger(OrderSubmitQueue.class);
    @Resource(name = "orderCommonService")
    protected OrderCommonService orderCommonService;

    /**
     * @return com.graduation.domain.bo.OrderBO
     * @Author fuxiaoxiang2
     * @Description 解析订单
     * @Date 9:58 2019/1/29
     * @Param [message]
     **/
    protected OrderBO parseOrder(Object message) {
        Message content = (Message) message;
        byte[] bytes = content.getBody();
        return JSONObject.parseObject(bytes, OrderBO.class);
    }

    protected abstract void consumer(Object message);
}
