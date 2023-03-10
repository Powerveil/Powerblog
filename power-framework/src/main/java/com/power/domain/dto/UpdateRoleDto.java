package com.power.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author power
 * @Date 2023/2/12 16:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleDto {
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
     * 备注
     */
    private String remark;//

    private List<Long> menuIds;

}
