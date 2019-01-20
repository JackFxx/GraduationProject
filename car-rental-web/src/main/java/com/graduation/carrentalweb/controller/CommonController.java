package com.graduation.carrentalweb.controller;

import com.graduation.common.enums.ApiEnum;
import com.graduation.domain.dto.CommonResponse;
import com.graduation.domain.po.CategoryPO;
import com.graduation.domain.vo.CategoryVO;
import com.graduation.service.CategoryService;
import com.graduation.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 通用
 * @Author fuxiaoxiang2
 * @Create 2019/1/17 16:17
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @Resource(name = "commonService")
    private CommonService commonService;

    /**
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author fuxiaoxiang2
     * @Description
     * @Date 16:22 2019/1/17
     * @Param [response, request]
     **/
    @RequestMapping("/home/category")
    public @ResponseBody
    CommonResponse buildHomePage() {
        try {
            CategoryPO po = new CategoryPO();
            List<CategoryVO> voList = categoryService.listCategory(po);
            return CommonResponse.success(voList);
        } catch (Exception e) {
            LOGGER.error("build home page error", e);
            return CommonResponse.failed(ApiEnum.SERVER_ERROR);
        }
    }

    /**
     * @return com.graduation.domain.dto.CommonResponse
     * @Author fuxiaoxiang2
     * @Description 拉取首页banner图
     * @Date 18:31 2019/1/17
     * @Param []
     **/
    @RequestMapping(value = "/home/banner")
    public @ResponseBody
    CommonResponse pullIndexBanner() {
        List<String> bannerUrlList = commonService.pullHomeBanner();
        return CommonResponse.success(bannerUrlList);
    }

    /**
     * @return com.graduation.domain.dto.CommonResponse
     * @Author fuxiaoxiang2
     * @Description 存储banner图
     * @Date 18:31 2019/1/18
     * @Param []
     **/
    @RequestMapping(value = "/upload/banner")
    public @ResponseBody
    CommonResponse saveBanner(@RequestParam("bannerPath") MultipartFile bannerPath) {
        try {
            commonService.pushBanner(bannerPath);
            return CommonResponse.success();
        }catch (Exception e){
            LOGGER.error("save banner",e);
            return CommonResponse.failed(ApiEnum.SERVER_ERROR);
        }
    }
}
