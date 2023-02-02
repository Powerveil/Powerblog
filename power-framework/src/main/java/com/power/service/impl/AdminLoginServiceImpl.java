package com.power.service.impl;

import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.LoginUser;
import com.power.domain.entity.User;
import com.power.domain.vo.BlogUserLoginVo;
import com.power.domain.vo.UserInfoVo;
import com.power.service.AdminLoginService;
import com.power.service.BlogLoginService;
import com.power.utils.BeanCopyUtils;
import com.power.utils.JwtUtil;
import com.power.utils.RedisCache;
import com.power.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author power
 * @Date 2023/1/12 17:03
 */
@Component
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject(SystemConstants.JWT_ADMIN_KEY_PREFIX + userId, loginUser);

        //把token封装 返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        // 获取当前登录的用户id
        Long userId = SecurityUtils.getUserId();
        // 删除redis中对应的值
        redisCache.deleteObject(SystemConstants.JWT_ADMIN_KEY_PREFIX + userId);
        return ResponseResult.okResult();
    }
}
