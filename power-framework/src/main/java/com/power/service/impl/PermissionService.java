package com.power.service.impl;

import com.power.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author power
 * @Date 2023/2/2 14:01
 */
@Service("ps")
public class PermissionService {

    /**
     * 判断当前用户是否具有permission
     * @param permission 要判断的权限
     * @return 是否具有权限
     */
    public boolean hasPermission(String permission) {
        // 如果是超级管理员 直接返回true
        if (SecurityUtils.isAdmin()) {
            return true;
        }
        // 否则 获取当前登录用户所具有的权限列表 如何判断是否存在permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
