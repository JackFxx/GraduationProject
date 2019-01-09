package com.graduation.domain.base;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 基本查询实体，封装了分页相关信息
 * @Author fuxiaoxiang2
 * @Create 2019/1/6 19:57
 */
public class BaseQueryParam implements Serializable{
    /**
     * 页码
     */
    private Integer pageNo = 1;
    /**
     * 偏移量
     */
    private Integer offset;
    /**
     * 单页数据大小 默认为10
     */
    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    private String sortFiled;
    /**
     * 排序方式 默认为desc
     */
    private String sortOrder = "desc";

    public void setPageNo(Integer pageNo) {
        if(pageNo == null || pageNo<1){
            pageNo = 1;
        }
        if(pageSize<1){
            pageSize = 1;
        }
        this.pageNo = pageNo;
        this.offset = (pageNo-1)*pageSize;
    }
}
