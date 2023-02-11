package com.power.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.entity.RoleMenu;
import com.power.service.RoleMenuService;
import com.power.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author power
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service实现
* @createDate 2023-02-11 22:16:26
*/
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
    implements RoleMenuService{

}




