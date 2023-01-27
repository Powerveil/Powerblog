package com.power.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author power
 * @Date 2023/1/27 16:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {
    private String name;

    private String remark;

}
