package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author power
 * @Date 2023/1/22 22:15
 */
@RestController
@RequestMapping("//content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list() {
        return ResponseResult.okResult(tagService.list());
    }
}
