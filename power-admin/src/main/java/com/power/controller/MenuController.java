package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
