package com.power.mapper;

import com.power.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author power
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2023-01-23 20:12:48
* @Entity com.power.domain.entity.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);
}




