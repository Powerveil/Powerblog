package com.power.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author power
 * @Date 2023/3/6 22:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;

    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
}
