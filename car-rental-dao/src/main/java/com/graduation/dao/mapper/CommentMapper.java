package com.graduation.dao.mapper;

import com.graduation.dao.base.BaseCrudMapper;
import com.graduation.dao.base.BaseQueryMapper;
import com.graduation.domain.bo.CommentBO;
import com.graduation.domain.po.CommentPO;
import com.graduation.domain.vo.CommentVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/24 12:38
 */
public interface CommentMapper extends BaseCrudMapper<CommentBO>, BaseQueryMapper<CommentBO, CommentPO> {
    List<CommentVO> listComment(CommentPO commentPO);
}
