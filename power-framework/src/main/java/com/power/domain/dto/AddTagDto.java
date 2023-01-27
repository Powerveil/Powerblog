package com.power.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/1/27 17:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTagDto {
    private String name;

    private String remark;
}
