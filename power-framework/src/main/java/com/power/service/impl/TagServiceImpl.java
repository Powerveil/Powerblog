package com.power.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.entity.Tag;
import com.power.service.TagService;
import com.power.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author power
* @description 针对表【power_tag(标签)】的数据库操作Service实现
* @createDate 2023-01-22 22:28:34
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{
}




