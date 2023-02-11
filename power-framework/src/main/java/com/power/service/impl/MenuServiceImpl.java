package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.dto.MenuDto;
import com.power.domain.entity.Menu;
import com.power.domain.vo.ListMenuVo;
import com.power.domain.vo.MenuListVo;
import com.power.domain.vo.MenuVo;
import com.power.enums.AppHttpCodeEnum;
import com.power.service.MenuService;
import com.power.mapper.MenuMapper;
import com.power.utils.BeanCopyUtils;
import com.power.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Override
    @Transactional
    public ResponseResult addMenu(Menu menu) {
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenuById(Long id) {
        if (Objects.isNull(id)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_NOT_NULL);
        }
        Menu menu = getById(id);
        MenuDto menuDto = BeanCopyUtils.copyBean(menu, MenuDto.class);
        return ResponseResult.okResult(menuDto);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        if (!menu.getParentId().equals(menu.getId())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_MENU_PARENT_ERROR);
        }
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getId, menu.getId());
        update(menu, queryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenuById(Long id) {
        Menu menu = getById(id);
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, id);
        int count = count(queryWrapper);
        if (count > 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_MENU_CHILDREN_ERROR);
        }
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult treeSelect() {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, SystemConstants.MENU_PARENT_ID);
        queryWrapper.orderByAsc(Menu::getOrderNum);
        // 找到所有根节点
        List<Menu> list = list(queryWrapper);

        List<MenuListVo> menuListVos = BeanCopyUtils.copyBeanList(list, MenuListVo.class);
        // 查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (MenuListVo menuListVo : menuListVos) {
            List<MenuListVo> children = getChildren2(menuListVo.getId());
            menuListVo.setChildren(children);
        }
        return ResponseResult.okResult(menuListVos);
    }

    private List<MenuListVo> getChildren2(Long id) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, id);
        queryWrapper.orderByAsc(Menu::getOrderNum);
        List<Menu> list = list(queryWrapper);
        if (Objects.isNull(list)) {
            return null;
        }
        List<MenuListVo> menuListVos = BeanCopyUtils.copyBeanList(list, MenuListVo.class);
        for (MenuListVo menuListVo : menuListVos) {
            List<MenuListVo> children2 = getChildren2(menuListVo.getId());
            menuListVo.setChildren(children2);
        }
        return menuListVos;
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




