package com.power.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Article;
import com.power.domain.entity.User;

/**
 * @author power
 * @Date 2023/1/23 14:45
 */
public interface AdminLoginService {
    ResponseResult login(User user);
}
