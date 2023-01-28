package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddTagDto;
import com.power.domain.dto.TagListDto;
import com.power.domain.dto.UpdateTagDto;
import com.power.domain.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.power.domain.vo.TagVo;

import java.util.List;

/**
* @author power
* @description 针对表【power_tag(标签)】的数据库操作Service
* @createDate 2023-01-22 22:28:34
*/
public interface TagService extends IService<Tag> {

    ResponseResult pageRagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(AddTagDto addTagDto);

    ResponseResult deleteTagById(Long id);

    ResponseResult updateTag(UpdateTagDto updateTagDto);

    List<TagVo> listAllTag();
}
