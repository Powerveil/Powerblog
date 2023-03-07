package com.power.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/3/7 15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLinkDto {
    /**
     * 友链名称
     */
    private String name;//

    /**
     * 友链logo
     */
    private String logo;//

    /**
     * 友链描述
     */
    private String description;//

    /**
     * 网站地址
     */
    private String address;//

    /**
     * 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
     */
    private String status;//
}
