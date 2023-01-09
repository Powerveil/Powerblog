package com.power.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/1/9 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo extends ArticleListVo {
    /**
     * 文章内容
     */
    private String content;
}
