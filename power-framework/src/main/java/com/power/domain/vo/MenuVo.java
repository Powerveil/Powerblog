package com.power.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author power
 * @Date 2023/1/26 16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {
    /**
     * 菜单ID
     */
    @TableId(type = IdType.AUTO)
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
     * 组件路径
     */
    private String component;//

    /**
     * 权限标识
     */
    private String perms;

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
     * 创建时间
     */
    private Date createTime;//


    private List<MenuVo> children;//
}
