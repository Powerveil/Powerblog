package com.power.job;

import com.power.constants.SystemConstants;
import com.power.domain.entity.Article;
import com.power.handler.mybatisplus.MyMetaObjectHandler;
import com.power.service.ArticleService;
import com.power.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author power
 * @Date 2023/1/19 15:29
 */
@Component
@Slf4j
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;


//    @Scheduled(cron = "0/5 * * * * ?")
//    public void updateViewCount() {
//        // 获取redis中的浏览量
//        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT_KEY);
//
//        List<Article> articles = viewCountMap.entrySet()
//                .stream()
//                .map(entry -> new Article(Long.valueOf(entry.getKey()), Long.valueOf(entry.getValue())))
//                .collect(Collectors.toList());
//
////        System.out.println("==================================================");
////        System.out.println("==================================================");
////        System.out.println("==================================================");
////        System.out.println(articles);
////        System.out.println("==================================================");
////        System.out.println("==================================================");
////        System.out.println("==================================================");
////
////        List<Long> ids = new ArrayList<>();
////        List<Long> viewCounts = new ArrayList<>();
////
////        for (Article article : articles) {
////            ids.add(article.getId());
////            viewCounts.add(article.getViewCount());
////        }
////
////        List<Article> stableArticles = articleService.listByIds(ids);
////        for (int i = 0; i < stableArticles.size(); i++) {
////            stableArticles.get(i).setViewCount(viewCounts.get(i));
////        }
//        // 更新到数据库中
//        articleService.updateBatchById(articles);
//
//    }

//    @Scheduled(cron = "0/10 * * * * ?")
//    public void updateViewCount() {
//        //获取redis中的浏览量
//        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
//        //获取Id集合
//        List<Long> collect = viewCountMap.keySet()
//                .stream()
//                .map(Long::valueOf)
//                .collect(Collectors.toList());
//        //从数据库查到的数据
//        List<Article> articles = new ArrayList<>(articleService.listByIds(collect));
//        //这个主要简化更改内容的，如果直接使用articles也可以，但是很多无效修改
//        List<Article> articles1 = new ArrayList<>();
//        for (Article temp : articles) {
//            Article article = new Article(temp.getId(), temp.getViewCount());
//            article.setUpdateBy(temp.getUpdateBy());
//            article.setUpdateTime(temp.getUpdateTime());
//            articles1.add(article);
//        }
//        //更新到数据库中
//        articleService.updateBatchById(articles1);
//    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void updateViewCount() {
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        log.info("viewCountMap={}", viewCountMap);
        //更新到数据库中
        if (!Objects.isNull(viewCountMap)) {
            articleService.updateViewCount(viewCountMap);
        }
    }
}
