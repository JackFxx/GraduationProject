package com.graduation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.graduation.dao.mapper.CommentMapper;
import com.graduation.domain.bo.CommentBO;
import com.graduation.domain.po.CommentPO;
import com.graduation.domain.vo.CommentVO;
import com.graduation.service.CommentService;
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
 * @Create 2019/1/24 14:17
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    private static final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentMapper commentMapper;

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 插入评论
     * @Date 14:18 2019/1/24
     * @Param [commentBO]
     **/
    @Override
    public int saveComment(CommentBO commentBO) {
        if (null == commentBO) {
            return -1;
        }
        commentBO.setCreateTime(System.currentTimeMillis());
        int resultCount = -1;
        try {
            resultCount = commentMapper.insert(commentBO);
        } catch (Exception e) {
            LOG.error("saveComment:{},error", JSONObject.toJSONString(commentBO), e);
        }
        return resultCount;
    }

    /**
     * @return java.util.List<com.graduation.domain.vo.CommentVO>
     * @Author fuxiaoxiang2
     * @Description 展示评论
     * @Date 14:18 2019/1/24
     * @Param [po]
     **/
    @Override
    public List<CommentVO> listComments(CommentPO po) {
        if (null == po || null == po.getCategoryId()) {
            LOG.warn("listComments must input categoryId");
            return null;
        }
        List<CommentVO> voList = null;
        LOG.info("commentPO:{}",JSONObject.toJSONString(po));
        try {
            voList = commentMapper.listComment(po);
        } catch (Exception e) {
            LOG.error("listComment:{},error", JSONObject.toJSONString(po), e);
        }
        return voList;
    }
}
