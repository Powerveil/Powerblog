package com.power.runner;

import com.power.constants.SystemConstants;
import com.power.domain.entity.Article;
import com.power.service.ArticleService;
import com.power.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author power
 * @Date 2023/1/19 14:41
 */
@Component
@Slf4j
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        // 查询博客信息 id viewCount
        List<Article> articles = articleService.list();
        Map<String, Integer> viewCountMap = articles.stream()
                .filter(item -> item.getDelFlag().equals(SystemConstants.ARTICLE_IS_NOT_DELETE))
                .collect(Collectors.toMap(article1 -> article1.getId().toString(), article -> article.getViewCount().intValue()));
//        log.info("viewCountMap={}", viewCountMap);
        //存储到redis中
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT_KEY, viewCountMap);
    }
}
