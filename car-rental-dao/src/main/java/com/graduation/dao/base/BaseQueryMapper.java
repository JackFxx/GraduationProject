package com.graduation.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 特殊查询mapper
 * @Author fuxiaoxiang2
 * @Create 2019/1/6 19:54
 */
public interface BaseQueryMapper<T extends Serializable,P extends Serializable> {
    /**
     * 统计数量
     */
    int count(P p);

    /**
     * 查询列表
     */
    List<T> listEntity(P p);
}
