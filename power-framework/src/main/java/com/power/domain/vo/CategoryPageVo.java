package com.power.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/3/6 23:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPageVo {
    private Long id;
    /**
     * 分类名
     */
    private String name;
    //描述
    private String description;
    /**
     * 状态0:正常,1禁用
     */
    private String status;
}
