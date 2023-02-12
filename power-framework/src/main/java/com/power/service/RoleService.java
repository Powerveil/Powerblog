package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddRoleDto;
import com.power.domain.dto.RoleChangeStatusDto;
import com.power.domain.dto.UpdateRoleDto;
import com.power.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author power
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-01-23 20:23:50
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult roleList(Long pageNum, Long pageSize, String roleName, String status);

    ResponseResult changeStatus(RoleChangeStatusDto roleChangeStatusDto);

    ResponseResult addRole(AddRoleDto addRoleDto);

    ResponseResult getRole(Long id);

    ResponseResult deleteById(Long id);

    ResponseResult updateRole(UpdateRoleDto updateRoleDto);
}
