package com.graduation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.graduation.dao.mapper.OrderMapper;
import com.graduation.domain.bo.OrderBO;
import com.graduation.domain.po.OrderPO;
import com.graduation.service.OrderCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/28 11:26
 */
@Service("orderCommonService")
public class OrderCommonServiceImpl implements OrderCommonService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderCommonServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    /**
     * @Author fuxiaoxiang2
     * @Description 订单入库
     * @Date 11:28 2019/1/28
     * @Param [orderBO]
     * @return int
     **/
    @Override
    public int insertOrder(OrderBO orderBO) {
        if(null == orderBO){
            LOG.warn("please input order");
            return -1;
        }
        int count = -1;
        try {
            count = orderMapper.saveOrder(orderBO);
        }catch (Exception e){
            LOG.error("insertOrder:{},error", JSONObject.toJSONString(orderBO));
            return count;
        }
        return count;
    }

    @Override
    public List<OrderBO> queryOrder(OrderPO orderPO) {
        return null;
    }
}
