package com.graduation.domain.po;

import com.graduation.domain.base.BaseQueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 类目查询实体
 * @Author fuxiaoxiang2
 * @Create 2019/1/9 12:26
 */
@Data
public class CategoryPO extends BaseQueryParam implements Serializable {
    /**
     * 唯一ID
     */
    private Long id;
    /**
     * 名称
     */
    private String categoryName;
    /**
     * 描述
     */
    private String detailDes;
    /**
     * 类别
     */
    private Integer type;
    /**
     * 实时价格
     */
    private Long realPrice;
    /**
     * 划线价格
     */
    private Long linePrice;
    /**
     * 来源人
     */
    private String sourceUser;
    /**
     * 浏览量
     */
    private Integer viewNum;
    /**
     * 点赞数
     */
    private Integer likeNum;
    /**
     * 点踩数
     */
    private Integer hateNum;
    /**
     * 主图链接
     */
    private String mainImgUrl;
    /**
     * 次图链接
     */
    private String imgUrl;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 修改时间
     */
    private Long modifyTime;

}
