package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Article;
import com.power.domain.vo.HotArticleVo;
import com.power.service.ArticleService;
import com.power.mapper.ArticleMapper;
import com.power.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author power
* @description 针对表【power_article(文章表)】的数据库操作Service实现
* @createDate 2023-01-06 20:51:06
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

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
        List<Article> records = pageInfo.getRecords();
        // 使用自定义进行bean拷贝
        List<HotArticleVo> articleVOs = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);
        // 返回结果
        return ResponseResult.okResult(articleVOs);
    }
}




