package com.graduation.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 一些通用服务
 * @Author fuxiaoxiang2
 * @Create 2019/1/18 9:45
 */
public interface CommonService {
    /**
     * 拉取首页banner图
     * @param
     * @return
     */
    List<String> pullHomeBanner();

    /**
     * 存储banner图
     * @param file
     */
    void pushBanner(MultipartFile file);
}
