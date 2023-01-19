package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Article;
import com.power.domain.entity.Category;
import com.power.domain.vo.ArticleDetailVo;
import com.power.domain.vo.ArticleListVo;
import com.power.domain.vo.HotArticleVo;
import com.power.domain.vo.PageVo;
import com.power.service.ArticleService;
import com.power.mapper.ArticleMapper;
import com.power.service.CategoryService;
import com.power.utils.BeanCopyUtils;
import com.power.utils.PageUtils;
import com.power.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author power
* @description 针对表【power_article(文章表)】的数据库操作Service实现
* @createDate 2023-01-06 20:51:06
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult hostArticleList() {
        // 查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多只查询10条
        Page<Article> pageInfo = new Page<>(1, 10);
        page(pageInfo,queryWrapper);
        List<Article> articles = pageInfo.getRecords();
        // 从redis中获取viewCount
        setListCurrentViewCount(articles);
        // 使用自定义进行bean拷贝
        List<HotArticleVo> articleVOs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        // 返回结果
        return ResponseResult.okResult(articleVOs);
    }


    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 判断参数是否合法
        if (PageUtils.isIllegal(pageNum, pageSize)) {
            return ResponseResult.errorResult(0, "参数传递不正确"); // TODO 这个后期一定要改进
        }
        // 查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId.intValue() > 0, Article::getCategoryId, categoryId);
        // 状态是正式发布的
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        queryWrapper.orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> pageInfo = new Page<>(pageNum, pageSize);
        page(pageInfo, queryWrapper);
        // 查询categoryName
        List<Article> articles = pageInfo.getRecords();
        // 1. 使用stream流
        articles = articles.stream()
                .map(article -> {
                    setCurrentViewCount(article);
                    return article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
                })
                .collect(Collectors.toList());

        // 2. 使用for循环
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }

//        System.out.println(articles);
        // 封装查询结果vo
        List<ArticleListVo> articleListVos
                = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, pageInfo.getTotal());
        System.out.println(articleListVos);
        // 返回结果
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Integer id) {
        // 根据id查询文章
        Article article = getById(id);
        // 从redis中获取viewCount
        setCurrentViewCount(article);
        // 转换为VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类id查询分类名
        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        // 封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    // 从redis中查询viewCount并设置到该articles中
    private void setListCurrentViewCount(List<Article> articles) {
        for (Article article : articles) {
            setCurrentViewCount(article);
        }
    }

    // 从redis中查询viewCount并设置到该article中
    private void setCurrentViewCount(Article article) {
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY, article.getId().toString());
        if (!Objects.isNull(viewCount)) {
            article.setViewCount(viewCount.longValue());
        }
    }

    @Override
    public ResponseResult updateViewCount(Integer id) {
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY, id.toString(), 1);
        return ResponseResult.okResult();
    }



}




