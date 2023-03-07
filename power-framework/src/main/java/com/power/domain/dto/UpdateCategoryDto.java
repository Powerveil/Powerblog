package com.power.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/3/7 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryDto {
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
