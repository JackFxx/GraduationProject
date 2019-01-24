package com.graduation.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.enums.ApiEnum;
import com.graduation.domain.bo.CommentBO;
import com.graduation.domain.dto.CommonResponse;
import com.graduation.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/24 14:43
 */
@RequestMapping(value = "/comment")
@Controller
public class CommentController {
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Resource(name = "commentService")
    private CommentService commentService;

    @RequestMapping("/save")
    public @ResponseBody
    CommonResponse saveComment(CommentBO commentBO) {
        try {
            commentService.saveComment(commentBO);
            return CommonResponse.success();
        } catch (Exception e) {
            LOG.error("saveComment:{},error", JSONObject.toJSONString(commentBO), e);
            return CommonResponse.failed(ApiEnum.SERVER_ERROR);
        }
    }
}
