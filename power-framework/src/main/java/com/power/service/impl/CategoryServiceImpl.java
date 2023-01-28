package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Article;
import com.power.domain.entity.Category;
import com.power.domain.vo.CategoryVo;
import com.power.service.ArticleService;
import com.power.service.CategoryService;
import com.power.mapper.CategoryMapper;
import com.power.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author power
* @description 针对表【power_category(分类表)】的数据库操作Service实现
* @createDate 2023-01-08 17:06:25
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

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
}




