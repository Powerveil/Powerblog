package com.power.job;

import com.power.constants.SystemConstants;
import com.power.domain.entity.Article;
import com.power.service.ArticleService;
import com.power.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author power
 * @Date 2023/1/19 15:29
 */
@Component
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

    @Scheduled(cron = "0/20 * * * * ?")
    public void updateViewCount() {
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");

        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);
    }
}
