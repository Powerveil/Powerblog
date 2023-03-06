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
 * @Date 2023/3/6 22:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    /**
     * 主键
     */
    private Long id;//

    /**
     * 用户名
     */
    private String userName;//

    /**
     * 昵称
     */
    private String nickName;//

    /**
     * 账号状态（0正常 1停用）
     */
    private String status;//

    /**
     * 邮箱
     */
    private String email;//

    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;//

    List<Long> roleIds;
}
