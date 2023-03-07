package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddCategoryDto;
import com.power.domain.dto.UpdateCategoryDto;
import com.power.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.power.domain.vo.CategoryVo;

import java.util.List;

/**
* @author power
* @description 针对表【power_category(分类表)】的数据库操作Service
* @createDate 2023-01-08 17:06:25
*/
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    ResponseResult categoryList(Long pageNum, Long pageSize, String name, String status);

    ResponseResult addCategory(AddCategoryDto addCategoryDto);

    ResponseResult getCategoryById(Long id);

    ResponseResult updateCategory(UpdateCategoryDto updateCategoryDto);

    ResponseResult deleteCategory(Long id);
}
