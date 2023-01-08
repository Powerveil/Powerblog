package com.power.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/1/7 11:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 访问量
     */
    private Long viewCount;
}
