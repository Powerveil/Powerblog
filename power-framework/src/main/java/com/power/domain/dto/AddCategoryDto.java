package com.power.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/3/6 23:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryDto {
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
