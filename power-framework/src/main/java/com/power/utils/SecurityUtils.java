package com.power.utils;

import com.power.constants.SystemConstants;
import com.power.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author power
 * @Date 2023/1/15 16:33
 */
public class SecurityUtils {
    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return SystemConstants.USER_ADMIN_ID.equals(id);
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}
