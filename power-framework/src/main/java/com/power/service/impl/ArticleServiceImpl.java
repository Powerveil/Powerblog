package com.power.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.entity.Article;
import com.power.service.ArticleService;
import com.power.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author power
* @description 针对表【power_article(文章表)】的数据库操作Service实现
* @createDate 2023-01-06 20:51:06
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




