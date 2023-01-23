package com.power.mapper;

import com.power.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author power
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2023-01-23 20:23:50
* @Entity com.power.domain.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}




