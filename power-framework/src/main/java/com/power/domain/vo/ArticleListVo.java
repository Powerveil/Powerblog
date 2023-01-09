package com.power.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author power
 * @Date 2023/1/9 11:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 所属分类id
     */
    private Long categoryId;
    /**
     * 所属分类名字
     */
    private String categoryName;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 访问量
     */
    private Long viewCount;
    /**
     *
     */
    private Date createTime;
}
