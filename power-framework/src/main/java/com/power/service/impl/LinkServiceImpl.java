package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Link;
import com.power.domain.vo.LinkVo;
import com.power.service.LinkService;
import com.power.mapper.LinkMapper;
import com.power.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author power
* @description 针对表【power_link(友链)】的数据库操作Service实现
* @createDate 2023-01-09 15:18:52
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

    @Override
    public ResponseResult getAllLink() {
        // 查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(queryWrapper);
        // 转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        System.out.println(linkVos);
        // 封装返回
        return ResponseResult.okResult(linkVos);
    }
}




