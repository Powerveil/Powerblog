package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.ResponseResult;
import com.power.domain.dto.AddTagDto;
import com.power.domain.dto.TagListDto;
import com.power.domain.dto.UpdateTagDto;
import com.power.domain.entity.Tag;
import com.power.domain.vo.PageVo;
import com.power.domain.vo.TagVo;
import com.power.enums.AppHttpCodeEnum;
import com.power.service.TagService;
import com.power.mapper.TagMapper;
import com.power.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
* @author power
* @description 针对表【power_tag(标签)】的数据库操作Service实现
* @createDate 2023-01-22 22:28:34
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{
    @Override
    public ResponseResult pageRagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        // 查询数据
        Page<Tag> pageInfo = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());

        page(pageInfo, queryWrapper);
        // 封装vo
        List<Tag> records = pageInfo.getRecords();
        List<TagVo> tagVoList = BeanCopyUtils.copyBeanList(records, TagVo.class);

        PageVo result = new PageVo(tagVoList, pageInfo.getTotal());
        return ResponseResult.okResult(result);
    }

    @Override
    public ResponseResult addTag(AddTagDto addTagDto) {
        // 判断参数是否为空
        if (Objects.isNull(addTagDto)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_NOT_NULL);
        }
        // name不能重复
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(addTagDto.getName()), Tag::getName, addTagDto.getName());
        if (count(queryWrapper) > 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.TAG_EXIST);
        }
        // 添加数据
        Tag tag = BeanCopyUtils.copyBean(addTagDto, Tag.class);
        save(tag);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTagById(Long id) {
        if (removeById(id)) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
    }

    @Override
    public ResponseResult updateTag(UpdateTagDto updateTagDto) {
        if (Objects.isNull(updateTagDto)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_NOT_NULL);
        }
        Tag tag = BeanCopyUtils.copyBean(updateTagDto, Tag.class);
        if (updateById(tag)) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_ERROR);
        }
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }

}




