package com.graduation.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.enums.ApiEnum;
import com.graduation.domain.bo.CategoryBO;
import com.graduation.domain.dto.CommonResponse;
import com.graduation.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 类目相关信息层
 * @Author fuxiaoxiang2
 * @Create 2019/1/18 15:23
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @RequestMapping(value = "/save")
    public @ResponseBody
    CommonResponse saveCategory(CategoryBO categoryBO, @RequestParam("files") MultipartFile[] files) {
        try {
            int count = categoryService.saveCategory(categoryBO, files);
            if(count != -1){
                return CommonResponse.success();
            }else {
                return CommonResponse.failed(ApiEnum.OPERATION_FAILED);
            }
        } catch (Exception e) {
            LOG.error("saveCategory:{},error", JSONObject.toJSONString(categoryBO), e);
            return CommonResponse.failed(ApiEnum.SERVER_ERROR);
        }
    }
}
