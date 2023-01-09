package com.power.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author power
 * @Date 2023/1/9 15:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {
    private Long id;
    /**
     * 友链名称
     */
    private String name;

    /**
     * 友链logo
     */
    private String logo;

    /**
     * 友链描述
     */
    private String description;

    /**
     * 网站地址
     */
    private String address;
}
