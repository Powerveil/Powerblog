package com.power.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author power
 * @Date 2023/1/9 11:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    private List rows;
    private Long total;
}
