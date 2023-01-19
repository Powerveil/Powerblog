package com.power;

import com.power.domain.entity.Article;
import com.power.service.ArticleService;
import com.power.utils.TestRedis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author power
 * @Date 2023/1/19 16:01
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.power"})
@SpringBootTest(classes = TestMybatisPlus.class)
public class TestMybatisPlus {

    @Autowired
    private ArticleService articleService;

    @Test
    public void test01() {
        List<Article> articles = articleService.list();
        articleService.updateBatchById(articles);
    }
}
