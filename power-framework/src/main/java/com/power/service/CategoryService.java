package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author power
* @description 针对表【power_category(分类表)】的数据库操作Service
* @createDate 2023-01-08 17:06:25
*/
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
