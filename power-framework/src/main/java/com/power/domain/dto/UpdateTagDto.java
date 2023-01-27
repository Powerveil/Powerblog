package com.power.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/1/27 22:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTagDto {
    private Long id;

    private String name;

    private String remark;
}
