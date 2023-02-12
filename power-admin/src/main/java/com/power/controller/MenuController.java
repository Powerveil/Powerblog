package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.domain.entity.Menu;
import com.power.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author power
 * @Date 2023/2/2 15:45
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult listMenu(String status, String menuName) {
        return menuService.listMenu(status, menuName);
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @GetMapping("{id}")
    public ResponseResult getMenuById(@PathVariable("id") Long id) {
        return menuService.getMenuById(id);
    }

    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteMenuById(@PathVariable("id") Long id) {
        return menuService.deleteMenuById(id);
    }

    @GetMapping("/treeselect")
    public ResponseResult treeSelect() {
        return menuService.treeSelect();
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult roleMenuTreeselect(@PathVariable("id") Long id) {
        return menuService.roleMenuTreeselect(id);
    }
}
