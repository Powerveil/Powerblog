package com.power.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友链
 * @TableName power_link
 */
@TableName(value ="power_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
     */
    private String status;

    /**
     * 
     */
    private Long createBy;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long updateBy;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}