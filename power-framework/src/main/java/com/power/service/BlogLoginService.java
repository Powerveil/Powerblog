package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.entity.User;

/**
 * @author power
 * @Date 2023/1/12 17:02
 */
public interface BlogLoginService {
    ResponseResult login(User user);
}
