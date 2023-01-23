package com.power.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author power
 * @Date 2023/1/23 20:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserInfoVo {
    private List<String> permissions;

    private List<String> roleKeyList;

    private UserInfoVo userInfoVo;
}
