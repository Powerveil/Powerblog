package com.power.filter;

import com.alibaba.fastjson.JSON;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.LoginUser;
import com.power.enums.AppHttpCodeEnum;
import com.power.utils.JwtUtil;
import com.power.utils.RedisCache;
import com.power.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author power
 * @Date 2023/1/13 15:50
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 说明该接口不需要登录 直接放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析获取userId
        String userId = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            // token超时 token非法
            // 响应告诉前端需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
        // 从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(SystemConstants.JWT_ADMIN_KEY_PREFIX + userId);
        // 如果获取不到
        if (Objects.isNull(loginUser)) {
            //说明登录过期  提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
