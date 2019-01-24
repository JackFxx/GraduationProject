package com.graduation.service;

import com.graduation.domain.bo.CommentBO;
import com.graduation.domain.po.CommentPO;
import com.graduation.domain.vo.CommentVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 评论基础服务
 * @Author fuxiaoxiang2
 * @Create 2019/1/24 14:14
 */
public interface CommentService {
    /**
     *
     * @param commentBO
     * @return
     */
    int saveComment(CommentBO commentBO);

    /**
     * 展示评论
     * @param po
     * @return
     */
    List<CommentVO> listComments(CommentPO po);
}
