package com.power.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.power.constants.SystemConstants;
import com.power.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author power
 * @Date 2023/1/15 16:40
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = SecurityUtils.getUserId();
//        try {
//            userId = SecurityUtils.getUserId();
//        } catch (Exception e) {
//            e.printStackTrace();
//            userId = SystemConstants.DEFAULT_REGISTER_USER_ID;//表示是自己创建
//        }
        if (Objects.isNull(userId)) {
            userId = SystemConstants.DEFAULT_REGISTER_USER_ID;//表示是自己创建
        }

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (!Objects.isNull(SecurityUtils.getUserId())) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
            this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
        }
    }
}
