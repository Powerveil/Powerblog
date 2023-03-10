package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.power.domain.vo.MenuVo;

import java.util.List;

/**
* @author power
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-01-23 20:12:48
*/
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult listMenu(String status, String menuName);

    ResponseResult addMenu(Menu menu);

    ResponseResult getMenuById(Long id);

    ResponseResult updateMenu(Menu menu);

    ResponseResult deleteMenuById(Long id);


    ResponseResult treeSelect();

    ResponseResult roleMenuTreeselect(Long id);
}
