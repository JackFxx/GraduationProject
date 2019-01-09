package com.graduation.dao.base;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 基本CRUD Mapper
 * @Author fuxiaoxiang2
 * @Create 2019/1/6 19:47
 */
public interface BaseCrudMapper<T extends Serializable> {
    /**
     * 插入实体
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 删除
     * @param entity
     * @return
     */
    int remove(T entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 查询
     * @param t
     * @return
     */
    T query(T t);
}
