package com.power.mapper;

import com.power.domain.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
* @author power
* @description 针对表【power_article(文章表)】的数据库操作Mapper
* @createDate 2023-01-06 20:51:06
* @Entity com.power.entity.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

}




