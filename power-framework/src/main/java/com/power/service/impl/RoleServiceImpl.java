package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.dto.AddRoleDto;
import com.power.domain.dto.RoleChangeStatusDto;
import com.power.domain.dto.UpdateRoleDto;
import com.power.domain.entity.RoleMenu;
import com.power.domain.entity.User;
import com.power.domain.entity.UserRole;
import com.power.domain.vo.ListAllRoleVo;
import com.power.domain.vo.RoleListVo;
import com.power.domain.entity.Role;
import com.power.domain.vo.PageVo;
import com.power.domain.vo.RoleVo;
import com.power.enums.AppHttpCodeEnum;
import com.power.service.RoleMenuService;
import com.power.service.RoleService;
import com.power.mapper.RoleMapper;
import com.power.utils.BeanCopyUtils;
import kotlin.jvm.internal.Lambda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* @author power
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2023-01-23 20:23:50
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 判断是否是管理员 如果是返回集合中只需要有admin
        if (SystemConstants.USER_ADMIN_ID.equals(id)) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        // 否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult roleList(Long pageNum, Long pageSize, String roleName, String status) {
        Page<Role> pageInfo = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(roleName), Role::getRoleName, roleName);
        queryWrapper.eq(StringUtils.hasText(status), Role::getStatus, status);

        queryWrapper.orderByAsc(Role::getRoleSort);

        page(pageInfo, queryWrapper);

        List<Role> records = pageInfo.getRecords();

        List<RoleListVo> roleListVos = BeanCopyUtils.copyBeanList(records, RoleListVo.class);

        PageVo result = new PageVo(roleListVos, pageInfo.getTotal());
        return ResponseResult.okResult(result);
    }

    @Override
    public ResponseResult changeStatus(RoleChangeStatusDto roleChangeStatusDto) {
        Long id = roleChangeStatusDto.getRoleId();
        if (Objects.isNull(id)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.ID_NOT_NULL);
        }
        String status = roleChangeStatusDto.getStatus();
        if (Objects.isNull(status)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.STATUS_NOT_NULL);
        }
        Role role = getById(id);
        role.setStatus(status);

        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getId, id);
        update(role, queryWrapper);

        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addRole(AddRoleDto addRoleDto) {
        Role role = BeanCopyUtils.copyBean(addRoleDto, Role.class);
        save(role);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleName, role.getRoleName());

        Role one = getOne(queryWrapper);
        List<Long> menuIds = addRoleDto.getMenuIds();
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(one.getId());
        for (Long menuId : menuIds) {
            roleMenu.setMenuId(menuId);
            roleMenuService.save(roleMenu);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteById(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateRole(UpdateRoleDto updateRoleDto) {
        Role role = BeanCopyUtils.copyBean(updateRoleDto, Role.class);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getId, role.getId());
        update(role, queryWrapper);

        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(role.getId());
        LambdaQueryWrapper<RoleMenu> queryWrapper2 = new LambdaQueryWrapper<>();

        //先删除再更新
        roleMenuService.removeById(role.getId());


        List<Long> menuIds = updateRoleDto.getMenuIds();
        for (Long menuId : menuIds) {
            roleMenu.setMenuId(menuId);
            roleMenuService.save(roleMenu);
        }

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllRole() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getStatus, SystemConstants.STATUS_NORMAL);
        List<Role> list = list(queryWrapper);
//        List<ListAllRoleVo> listAllRoleVos = BeanCopyUtils.copyBeanList(list, ListAllRoleVo.class);
        return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult getRole(Long id) {
        Role role = getById(id);

        RoleVo roleVo = BeanCopyUtils.copyBean(role, RoleVo.class);

        return ResponseResult.okResult(roleVo);
    }




}




