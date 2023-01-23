package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.entity.Menu;
import com.power.service.MenuService;
import com.power.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author power
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2023-01-23 20:12:48
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Override
    public List<String> selectPermsByUserId(Long id) {
        // 如果是管理员，返回所有的权限
        if (SystemConstants.USER_ADMIN_ID.equals(id)) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON);
            queryWrapper.in(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);
            return menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
        }
        // 否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }
}




