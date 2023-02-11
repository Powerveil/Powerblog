package com.power.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.entity.ArticleTag;
import com.power.service.ArticleTagService;
import com.power.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
* @author power
* @description 针对表【power_article_tag(文章标签关联表)】的数据库操作Service实现
* @createDate 2023-01-28 16:51:36
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




