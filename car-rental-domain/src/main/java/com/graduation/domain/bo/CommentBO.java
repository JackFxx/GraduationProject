package com.graduation.domain.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 评论相关信息表
 * @Author fuxiaoxiang2
 * @Create 2019/1/24 12:21
 */
@Data
public class CommentBO implements Serializable{
    /**
     * 唯一id
     */
    private Long id;

    /**
     * 所属类目id
     */
    private Long categoryId;

    /**
     * 评论人
     */
    private String commentUser;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long modifyTime;
}
