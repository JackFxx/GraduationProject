package com.graduation.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.constant.RabbitConstant;
import com.graduation.common.enums.ApiEnum;
import com.graduation.domain.bo.OrderBO;
import com.graduation.domain.dto.CommonResponse;
import com.graduation.res.mq.base.RabbitMqProvider;
import com.graduation.service.OrderService;
import com.graduation.web.config.CheckLogin;
import com.graduation.web.config.annotation.LimitAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/28 11:35
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Resource(name = "orderService")
    private OrderService orderService;

    @RequestMapping(value = "/submit")
    @CheckLogin
    @LimitAccess(time = 10000, count = 10)
    public @ResponseBody
    CommonResponse submitOrder(OrderBO orderBO) {
        try {
            int count = orderService.submitOrder(orderBO);
            if (count != 1) {
                return CommonResponse.failed("提单失败");
            }
        } catch (Exception e) {
            LOG.error("submitOrder:{},error", JSONObject.toJSONString(orderBO));
            return CommonResponse.failed(ApiEnum.SERVER_ERROR);
        }
        return CommonResponse.success();
    }
    @RequestMapping(value = "/pay")
    @CheckLogin
    @LimitAccess(time = 5000, count = 2)
    public @ResponseBody
    CommonResponse payOrder(OrderBO orderBO) {
        try {
            int count = orderService.payOrder(orderBO);
            if (count != 1) {
                return CommonResponse.failed("支付失败");
            }
        } catch (Exception e) {
            LOG.error("payOrder:{},error", JSONObject.toJSONString(orderBO));
            return CommonResponse.failed(ApiEnum.SERVER_ERROR);
        }
        return CommonResponse.success();
    }
    @RequestMapping(value = "/cancel")
    @CheckLogin
    @LimitAccess(time = 5000, count = 2)
    public @ResponseBody
    CommonResponse cancelOrder(OrderBO orderBO) {
        try {
            int count = orderService.cancelOrder(orderBO);
            if (count != 1) {
                return CommonResponse.failed("取消失败");
            }
        } catch (Exception e) {
            LOG.error("cancelOrder:{},error", JSONObject.toJSONString(orderBO));
            return CommonResponse.failed(ApiEnum.SERVER_ERROR);
        }
        return CommonResponse.success();
    }
}
