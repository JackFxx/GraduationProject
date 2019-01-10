package com.graduation.dao.mapper;

import com.graduation.dao.base.BaseCrudMapper;
import com.graduation.dao.base.BaseQueryMapper;
import com.graduation.dao.base.ISqlMapper;
import com.graduation.domain.bo.CategoryBO;
import com.graduation.domain.po.CategoryPO;

public interface CategoryMapper extends ISqlMapper,BaseCrudMapper<CategoryBO>,BaseQueryMapper<CategoryBO,CategoryPO> {
}
