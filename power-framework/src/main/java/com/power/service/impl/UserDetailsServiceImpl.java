package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.LoginUser;
import com.power.domain.entity.User;
import com.power.mapper.MenuMapper;
import com.power.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author power
 * @Date 2023/1/12 17:12
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserName, userName);
        User user = userService.getOne(queryWrapper);
        // 判断是否查到用户 如果没查到抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 返回用户信息
        // 查询权限信息封装
        // 如果是后台用户才需要查询权限封装
        if (user.getType().equals(SystemConstants.ADMIN)) {
            List<String> permissions = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user, permissions);
        }
        return new LoginUser(user, null);
    }
}
