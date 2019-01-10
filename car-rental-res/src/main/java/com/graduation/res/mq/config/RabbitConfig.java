package com.graduation.res.mq.config;

import com.graduation.common.constant.RabbitConstant;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author fuxiaoxiang2
 * @description rabbitmq相关配置
 * @create 2019/01/09
 */
@Configuration
public class RabbitConfig {

    //--------------------------队列配置------------------------//
    /**
     * 提单队列
     * @return
     */
    @Bean
    public Queue submitOrderQueue() {
        return new Queue(RabbitConstant.SUBMIT_ORDER_QUEUE);
    }
    /**
     * 支付队列
     * @return
     */
    @Bean
    public Queue payOrderQueue() {
        return new Queue(RabbitConstant.PAY_ORDER_QUEUE);
    }
    /**
     * 取消队列
     * @return
     */
    @Bean
    public Queue cancelOrderQueue() {
        return new Queue(RabbitConstant.CANCEL_ORDER_QUEUE);
    }
    //--------------------------交换机配置------------------------//

    /**
     * 交换机
     * @return
     */
    @Bean
    public Exchange exchange(){
        return new DirectExchange(RabbitConstant.DIRECT_EXCHANGE);
    }
}
