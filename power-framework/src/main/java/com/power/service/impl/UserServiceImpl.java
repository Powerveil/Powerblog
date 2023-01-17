package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.ResponseResult;
import com.power.domain.entity.User;
import com.power.domain.vo.UserInfoVo;
import com.power.enums.AppHttpCodeEnum;
import com.power.exception.SystemException;
import com.power.service.UserService;
import com.power.mapper.UserMapper;
import com.power.utils.BeanCopyUtils;
import com.power.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author power
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-01-12 16:47:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

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

    @Override
    public ResponseResult updateUserInfo(User user) {
        // TODO 代码太烂 需要改进
        // 判断传递参数是否为空
        if (Objects.isNull(user)) {
            throw new SystemException(AppHttpCodeEnum.USER_NOT_NULL);
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 以下信息不可含有
        boolean flag = Objects.isNull(user.getUserName())
                && Objects.isNull(user.getPassword())
                && Objects.isNull(user.getType())
                && Objects.isNull(user.getStatus())
                && Objects.isNull(user.getPhonenumber())
                && Objects.isNull(user.getCreateBy())
                && Objects.isNull(user.getCreateTime())
                && Objects.isNull(user.getDelFlag());

        System.out.println("=============================================");
        System.out.println("=============================================");
        System.out.println("=============================================");
        System.out.println(user);
        System.out.println("flag:" + flag);
        System.out.println("=============================================");
        System.out.println("=============================================");
        System.out.println("=============================================");

        queryWrapper.eq(!Objects.isNull(user.getId()) && flag, User::getId, user.getId());

//        queryWrapper.eq(Objects.isNull(user.getUserName()), User::getUserName, null);
//        queryWrapper.eq(Objects.isNull(user.getPassword()), User::getPassword, null);
//        queryWrapper.eq(Objects.isNull(user.getType()), User::getType, null);
//        queryWrapper.eq(Objects.isNull(user.getStatus()), User::getStatus, null);
//        queryWrapper.eq(Objects.isNull(user.getPhonenumber()), User::getPhonenumber, null);
//        queryWrapper.eq(Objects.isNull(user.getCreateBy()), User::getCreateBy, null);
//        queryWrapper.eq(Objects.isNull(user.getCreateTime()), User::getCreateTime, null);
//        queryWrapper.eq(Objects.isNull(user.getDelFlag()), User::getDelFlag, null);
        // 修改用户信息
//        boolean update = update(queryWrapper);
        boolean update = true;
        if (flag && !Objects.isNull(user.getId())) {
            update = updateById(user);
        }
        String modificationMessage = "修改成功";
        // 判断修改用户信息是否成功
        if (!update) {
            modificationMessage = "修改失败";
        }
        // 返回结果
        return ResponseResult.okResult(modificationMessage);
    }
}




