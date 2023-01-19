package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author power
* @description 针对表【power_article(文章表)】的数据库操作Service
* @createDate 2023-01-06 20:51:06
*/
public interface ArticleService extends IService<Article> {

    ResponseResult hostArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Integer id);

    ResponseResult updateViewCount(Integer id);
}
