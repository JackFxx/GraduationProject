package com.graduation.dao.mapper;

import com.graduation.dao.base.BaseCrudMapper;
import com.graduation.dao.base.BaseQueryMapper;
import com.graduation.dao.base.ISqlMapper;
import com.graduation.domain.bo.CategoryBO;
import com.graduation.domain.po.CategoryPO;
import com.graduation.domain.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper extends ISqlMapper, BaseCrudMapper<CategoryBO>, BaseQueryMapper<CategoryBO, CategoryPO> {
    /**
     * 查询类目信息
     *
     * @param po
     * @return
     */
    List<CategoryVO> listCategory(CategoryPO po);

    /**
     * 查看详细信息
     *
     * @param categoryId
     * @return
     */
    CategoryVO queryDetail(@Param("categoryId") Long categoryId);
}
