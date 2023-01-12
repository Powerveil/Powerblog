package com.power.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.entity.User;
import com.power.service.UserService;
import com.power.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author power
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-01-12 16:47:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




