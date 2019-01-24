package com.graduation.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/24 14:15
 */
@Data
public class CommentVO implements Serializable{
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
