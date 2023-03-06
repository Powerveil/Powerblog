package com.power.domain.vo;

import com.power.domain.entity.Role;
import com.power.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author power
 * @Date 2023/3/6 22:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsVo {
    List<Long> roleIds;

    List<Role> roles;

    GetUserVo user;
}
