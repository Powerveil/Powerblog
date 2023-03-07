package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.dto.AddLinkDto;
import com.power.domain.entity.Category;
import com.power.domain.entity.Link;
import com.power.domain.vo.LinkVo;
import com.power.domain.vo.PageListVo;
import com.power.domain.vo.PageVo;
import com.power.service.LinkService;
import com.power.mapper.LinkMapper;
import com.power.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public ResponseResult getPageListLink(Long pageNum, Long pageSize, String name, String status) {

        Page<Link> pageInfo = new Page(pageNum, pageSize);

        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.hasText(name), Link::getName, name);

        if (SystemConstants.STATUS_NORMAL.equals(status) || SystemConstants.STATUS_ABNORMAL.equals(status)) {
            queryWrapper.eq(Link::getStatus, status);
        }

        page(pageInfo, queryWrapper);

        List<Link> records = pageInfo.getRecords();

        List<PageListVo> pageListVos = BeanCopyUtils.copyBeanList(records, PageListVo.class);

        PageVo pageVo = new PageVo(pageListVos, pageInfo.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addLink(AddLinkDto addLinkDto) {
        Link link = BeanCopyUtils.copyBean(addLinkDto, Link.class);
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getLinkById(Long id) {
        Link link = getById(id);
        PageListVo pageListVo = BeanCopyUtils.copyBean(link, PageListVo.class);
        return ResponseResult.okResult(pageListVo);
    }

    @Override
    public ResponseResult updateLinkById(PageListVo updateDto) {
        Link link = BeanCopyUtils.copyBean(updateDto, Link.class);
        updateById(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteLinkById(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}




