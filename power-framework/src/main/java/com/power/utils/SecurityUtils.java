package com.power.utils;

import com.power.constants.SystemConstants;
import com.power.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author power
 * @Date 2023/1/15 16:33
 */
public class SecurityUtils {
    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        if (!Objects.isNull(getAuthentication())) {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        return null;
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        if (!Objects.isNull(getLoginUser())) {
            Long id = getLoginUser().getUser().getId();
            return SystemConstants.USER_ADMIN_ID.equals(id);
        }
        return false;
    }

    public static Long getUserId() {
        if (!Objects.isNull(getLoginUser())) {
            return getLoginUser().getUser().getId();
        }
        return null;
    }
}
