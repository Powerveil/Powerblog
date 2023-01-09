package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author power
* @description 针对表【power_link(友链)】的数据库操作Service
* @createDate 2023-01-09 15:18:52
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
