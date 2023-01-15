package com.power.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.ResponseResult;
import com.power.domain.entity.User;
import com.power.domain.vo.UserInfoVo;
import com.power.service.UserService;
import com.power.mapper.UserMapper;
import com.power.utils.BeanCopyUtils;
import com.power.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
* @author power
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-01-12 16:47:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public ResponseResult userInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据用户id查询用户信息
        User user = getById(userId);
        // 封装成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}




