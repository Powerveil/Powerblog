package com.power.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/2/7 16:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleChangeStatusDto {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
}
