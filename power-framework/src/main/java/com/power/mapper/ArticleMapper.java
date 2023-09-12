package com.power.mapper;

import com.power.domain.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

/**
* @author power
* @description 针对表【power_article(文章表)】的数据库操作Mapper
* @createDate 2023-01-06 20:51:06
* @Entity com.power.entity.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

    List<String> getTags(Long id);

    void updateViewCount(@Param("map") Map<String, Integer> viewCountMap);
}




