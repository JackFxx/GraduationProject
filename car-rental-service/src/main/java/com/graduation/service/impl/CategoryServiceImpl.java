package com.graduation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.util.PictureUtil;
import com.graduation.dao.mapper.CategoryMapper;
import com.graduation.domain.bo.CategoryBO;
import com.graduation.domain.po.CategoryPO;
import com.graduation.domain.po.CommentPO;
import com.graduation.domain.vo.CategoryVO;
import com.graduation.service.CategoryService;
import com.graduation.service.CommentService;
import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/9 14:45
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Resource(name = "commentService")
    private CommentService commentService;

    /**
     * @return int
     * @Author fuxiaoxiang2
     * @Description 录入类目信息
     * @Date 15:19 2019/1/9
     * @Param [bo, file] file第一张为主图，且必传
     **/
    @Override
    public int saveCategory(CategoryBO bo, MultipartFile[] file) {
        if (bo == null || null == file || file.length < 1) {
            LOG.warn("save category,receive a null object");
            return -1;
        }
        //生成唯一ID
        Long categoryId = createCategoryId();
        //图片存储处理，并返回地址 left为主图地址，right为次图地址，以&符号分割
        MutablePair<String, String> imgAddress = PictureUtil.parseAndSavePic(file, categoryId);
        bo.setCategoryId(categoryId);
        bo.setMainImgUrl(imgAddress.getLeft());
        bo.setImgUrl(imgAddress.getRight());
        bo.setCreateTime(System.currentTimeMillis());
        bo.setModifyTime(System.currentTimeMillis());
        LOG.info(JSONObject.toJSONString(imgAddress));
        int count = -1;
        try {
            count = categoryMapper.insert(bo);
        } catch (Exception e) {
            LOG.error("save category:{},occurs", categoryId, e);
        }
        return count;
    }

    /**
     * @return java.util.List<com.graduation.domain.vo.CategoryVO>
     * @Author fuxiaoxiang2
     * @Description 商品类目查询
     * @Date 14:46 2019/1/9
     * @Param [bo]
     **/
    @Override
    public List<CategoryVO> listCategory(CategoryPO categoryPO) {
        if (null == categoryPO) {
            LOG.warn("please input message");
            return null;
        }
        List<CategoryVO> categoryList = categoryMapper.listCategory(categoryPO);
        return categoryList;
    }

    /**
     * @return com.graduation.domain.vo.CategoryVO
     * @Author fuxiaoxiang2
     * @Description 详情页服务
     * @Date 14:36 2019/1/24
     * @Param [categoryPO]
     **/
    @Override
    public CategoryVO queryCategoryDetail(CategoryPO categoryPO) {
        if (null == categoryPO || null == categoryPO.getCategoryId()) {
            LOG.warn("queryCategoryDetail must need args");
            return null;
        }
        CategoryVO vo = new CategoryVO();
        try {
            Long categoryId = categoryPO.getCategoryId();
            vo = categoryMapper.queryDetail(categoryId);
            if(null == vo){
                LOG.warn("queryCategoryDetail:{},not exist",JSONObject.toJSONString(categoryPO));
                return null;
            }
            //构建评论查询参数
            CommentPO po = new CommentPO();
            po.setCategoryId(categoryId);
            vo.setVoList(commentService.listComments(po));
        } catch (Exception e) {
            LOG.error("queryCategoryDetail:{},error", JSONObject.toJSONString(categoryPO), e);
        }
        return vo;
    }

    /**
     * @return java.lang.Long
     * @Author fuxiaoxiang2
     * @Description 类目ID生成方案
     * @Date 14:32 2019/1/24
     * @Param []
     **/
    private Long createCategoryId() {
        Long categoryId = System.currentTimeMillis();
        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        categoryId = Long.parseLong(String.valueOf(num1) + String.valueOf(num2) + String.valueOf(categoryId));
        return categoryId;
    }
}
