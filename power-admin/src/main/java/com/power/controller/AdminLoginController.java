package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.domain.entity.LoginUser;
import com.power.domain.entity.User;
import com.power.domain.vo.AdminUserInfoVo;
import com.power.domain.vo.RoutersVo;
import com.power.domain.vo.UserInfoVo;
import com.power.enums.AppHttpCodeEnum;
import com.power.exception.SystemException;
import com.power.service.AdminLoginService;
import com.power.service.BlogLoginService;
import com.power.service.MenuService;
import com.power.service.RoleService;
import com.power.utils.BeanCopyUtils;
import com.power.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author power
 * @Date 2023/1/23 14:43
 */
@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            // 提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        // 获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 根据用户id查询信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        // 根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
//        List<String> roleKeyList = null;
        // 获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        // 封装数据返回

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);

        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters() {

    }
}
