package com.graduation.common.constant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/9 22:59
 */
public class RabbitConstant {
    /**
     * rabbitMq部分队列
     */
    public static final String TOUCH_LIKE_QUEUE = "touch-like";
    public static final String SUBMIT_ORDER_QUEUE = "submit-order";
    public static final String PAY_ORDER_QUEUE = "pay-order";
    public static final String CANCEL_ORDER_QUEUE = "cancel-order";

    /**
     * rabbitMq交换机类型
     */
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String FAN_OUT_EXCHANGE = "fanoutExchange";
    public static final String HEADER_EXCHANGE = "headerExchange";
}
