package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author power
 * @Date 2023/1/8 17:15
 */
@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }
}
