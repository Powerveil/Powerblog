package com.power.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.power.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author power
 * @Date 2023/2/7 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    /**
     * 菜单ID
     */
    private Long id;//

    /**
     * 菜单名称
     */
    private String menuName;//

    /**
     * 父菜单ID
     */
    private Long parentId;//

    /**
     * 显示顺序
     */
    private Integer orderNum;//

    /**
     * 路由地址
     */
    private String path;//

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;//

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;//

    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;//

    /**
     * 菜单图标
     */
    private String icon;//

    /**
     * 备注
     */
    private String remark;//
}
