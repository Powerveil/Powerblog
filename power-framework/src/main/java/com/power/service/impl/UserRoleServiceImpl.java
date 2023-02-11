package com.power.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.entity.UserRole;
import com.power.service.UserRoleService;
import com.power.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author power
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
* @createDate 2023-02-08 15:46:52
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




