package com.graduation.service;

import com.graduation.domain.bo.CategoryBO;
import com.graduation.domain.po.CategoryPO;
import com.graduation.domain.vo.CategoryVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 商品种类服务
 * @Author fuxiaoxiang2
 * @Create 2019/1/9 14:39
 */
public interface CategoryService {
    /**
     * 录入类目信息
     * @param bo
     * @param file 多张图片，第一张为主图，必选
     * @return
     */
    int saveCategory(CategoryBO bo,MultipartFile[] file);

    /**
     * 查询类目信息
     * @param categoryPO
     * @return
     */
    List<CategoryVO> listCategory(CategoryPO categoryPO);
}
