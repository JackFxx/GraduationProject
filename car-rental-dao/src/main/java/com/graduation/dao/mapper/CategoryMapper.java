package com.graduation.dao.mapper;

import com.graduation.dao.base.BaseCrudMapper;
import com.graduation.dao.base.BaseQueryMapper;
import com.graduation.dao.base.ISqlMapper;
import com.graduation.domain.bo.CategoryBO;
import com.graduation.domain.po.CategoryPO;
import com.graduation.domain.vo.CategoryVO;

import java.util.List;

public interface CategoryMapper extends ISqlMapper,BaseCrudMapper<CategoryBO>,BaseQueryMapper<CategoryBO,CategoryPO> {
    /**
     * 查询类目信息
     * @param po
     * @return
     */
    List<CategoryVO> listCategory(CategoryPO po);
}
