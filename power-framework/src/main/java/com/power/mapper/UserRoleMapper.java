package com.power.mapper;

import com.power.domain.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author power
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Mapper
* @createDate 2023-02-08 15:46:52
* @Entity com.power.domain.entity.UserRole
*/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    Boolean saveUserAndRole(@Param("userId") Long userId,
                            @Param("roleIds") List<Long> roleIds);
}




