package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddLinkDto;
import com.power.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.power.domain.vo.PageListVo;

/**
* @author power
* @description 针对表【power_link(友链)】的数据库操作Service
* @createDate 2023-01-09 15:18:52
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    ResponseResult getPageListLink(Long pageNum, Long pageSize, String name, String status);

    ResponseResult addLink(AddLinkDto addLinkDto);

    ResponseResult getLinkById(Long id);

    ResponseResult updateLinkById(PageListVo updateDto);

    ResponseResult deleteLinkById(Long id);
}
