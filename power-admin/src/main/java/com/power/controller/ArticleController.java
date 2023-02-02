package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddArticleDto;
import com.power.domain.entity.Article;
import com.power.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author power
 * @Date 2023/1/28 16:50
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult listArticle(Integer pageNum, Integer pageSize, String title, String summary) {
        return articleService.listArticle(pageNum, pageSize, title, summary);
    }

    @GetMapping("/{id}")
    public ResponseResult articleDetails(@PathVariable("id") Long id) {
        return articleService.articleDetails(id);
    }


    @PutMapping("/{id}")
    public ResponseResult updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") Long id) {
        articleService.removeById(id);
        return ResponseResult.okResult();
    }


}
