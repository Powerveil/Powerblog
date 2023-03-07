package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.dto.AddCategoryDto;
import com.power.domain.dto.UpdateCategoryDto;
import com.power.domain.entity.Article;
import com.power.domain.entity.Category;
import com.power.domain.vo.CategoryPageVo;
import com.power.domain.vo.CategoryVo;
import com.power.domain.vo.PageVo;
import com.power.service.ArticleService;
import com.power.service.CategoryService;
import com.power.mapper.CategoryMapper;
import com.power.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author power
 * @description 针对表【power_category(分类表)】的数据库操作Service实现
 * @createDate 2023-01-08 17:06:25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        // 查询文章表 状态为已发布的文章
        LambdaQueryWrapper<Article> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(queryWrapper1);
        // 获取文章的分类id，并且去重
        Set<Long> categoryIds = articles.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        // 查询分类表
//        LambdaQueryWrapper<Category> queryWrapper2 = new LambdaQueryWrapper<>();
//        queryWrapper2.eq(Category::getStatus, SystemConstants.CATEGORY_STATUS_NORMAL);
//        List<Category> categories = listBy
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        // 封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        // 返回结果
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;
    }

    @Override
    public ResponseResult categoryList(Long pageNum, Long pageSize, String name, String status) {
        Page<Category> pageInfo = new Page(pageNum, pageSize);

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        if (SystemConstants.STATUS_NORMAL.equals(status) || SystemConstants.STATUS_ABNORMAL.equals(status)) {
            queryWrapper.eq(Category::getStatus, status);
        }
        queryWrapper.like(!Objects.isNull(name), Category::getName, name);

        page(pageInfo, queryWrapper);

        List<Category> records = pageInfo.getRecords();
        List<CategoryPageVo> categoryPageVos = BeanCopyUtils.copyBeanList(records, CategoryPageVo.class);

        PageVo pageVo = new PageVo();
        pageVo.setRows(categoryPageVos);
        pageVo.setTotal(pageInfo.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addCategory(AddCategoryDto addCategoryDto) {
        Category category = BeanCopyUtils.copyBean(addCategoryDto, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCategoryById(Long id) {
        Category category = getById(id);
        CategoryPageVo categoryPageVo = BeanCopyUtils.copyBean(category, CategoryPageVo.class);
        return ResponseResult.okResult(categoryPageVo);
    }

    @Override
    public ResponseResult updateCategory(UpdateCategoryDto updateCategoryDto) {
        Category category = BeanCopyUtils.copyBean(updateCategoryDto, Category.class);
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteCategory(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}




