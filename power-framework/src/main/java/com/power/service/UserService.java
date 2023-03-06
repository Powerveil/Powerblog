package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddUserDto;
import com.power.domain.dto.UpdateUserDto;
import com.power.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author power
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-01-12 16:47:58
*/
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult userList(Long pageNum, Long pageSize, String userName, String phonenumber, String status);

    ResponseResult addUser(AddUserDto addUserDto);

    ResponseResult userDelete(Long id);

    ResponseResult getUserById(Long id);

    ResponseResult updateUser(UpdateUserDto updateUserDto);
}
