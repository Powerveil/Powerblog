package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddUserDto;
import com.power.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author power
 * @Date 2023/2/8 15:11
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public ResponseResult userList(Long pageNum, Long pageSize, String userName, String phonenumber, String status) {
        return userService.userList(pageNum, pageSize , userName, phonenumber, status);
    }

    @PostMapping
    public ResponseResult addUser(@RequestBody AddUserDto addUserDto) {
        return userService.addUser(addUserDto);
    }


}
