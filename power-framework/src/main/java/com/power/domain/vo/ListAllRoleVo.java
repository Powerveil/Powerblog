package com.power.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author power
 * @Date 2023/2/28 22:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAllRoleVo {
    /**
     * 角色ID
     */
    private Long id;//

    /**
     * 角色名称
     */
    private String roleName;//

    /**
     * 角色权限字符串
     */
    private String roleKey;//

    /**
     * 显示顺序
     */
    private Integer roleSort;//

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;//

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;//

    /**
     * 创建者
     */
    private Long createBy;//

    /**
     * 创建时间
     */
    private Date createTime;//

    /**
     * 更新者
     */
    private Long updateBy;//


    /**
     * 备注
     */
    private String remark;//
}
