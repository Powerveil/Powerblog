package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Menu;
import com.power.domain.vo.ListMenuVo;
import com.power.domain.vo.MenuVo;
import com.power.service.MenuService;
import com.power.mapper.MenuMapper;
import com.power.utils.BeanCopyUtils;
import com.power.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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
        if (SecurityUtils.isAdmin()) {
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

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        // 判断是否是管理员
        if (SecurityUtils.isAdmin()) {
            // 如果是 返回所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            // 否则 当前用户具有Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        // 构建tree
        // 先找出第一层的菜单，然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus, SystemConstants.MENU_PARENT_ID);

        return menuTree;
    }

    @Override
    public ResponseResult listMenu(String status, String menuName) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.hasText(menuName), Menu::getMenuName, menuName);
        queryWrapper.eq(StringUtils.hasText(status), Menu::getStatus, status);

        queryWrapper.orderByAsc(Menu::getParentId);
        queryWrapper.orderByAsc(Menu::getOrderNum);

        List<Menu> menus = list(queryWrapper);

        List<ListMenuVo> listMenuVos = BeanCopyUtils.copyBeanList(menus, ListMenuVo.class);

        return ResponseResult.okResult(listMenuVos);
    }

    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        return menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }


    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
    }
}




