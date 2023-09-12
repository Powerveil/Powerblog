package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddArticleDto;
import com.power.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author power
* @description 针对表【power_article(文章表)】的数据库操作Service
* @createDate 2023-01-06 20:51:06
*/
public interface ArticleService extends IService<Article> {

    ResponseResult hostArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Integer id);

    ResponseResult updateRedisViewCount(Integer id);

    ResponseResult add(AddArticleDto article);

    ResponseResult listArticle(Integer pageNum, Integer pageSize, String title, String summary);

    ResponseResult articleDetails(Long id);

    ResponseResult updateArticle(Article article);

    void updateViewCount(Map<String, Integer> viewCountMap);
}
