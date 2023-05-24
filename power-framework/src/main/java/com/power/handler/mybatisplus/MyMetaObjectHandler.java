package com.power.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.power.constants.SystemConstants;
import com.power.utils.SecurityUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.xmlbeans.impl.jam.mutable.MMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author power
 * @Date 2023/1/15 16:40
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = SecurityUtils.getUserId();
        if (Objects.isNull(userId)) {
            userId = SystemConstants.DEFAULT_REGISTER_USER_ID;//表示是自己创建
        }
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 如果updateTime有数据就不填充了
        if (Objects.isNull(metaObject.getValue("updateTime"))) {
            log.info("updateTime is null");
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }
        // 如果updateBy有数据就不填充了
        if (Objects.isNull(metaObject.getValue("updateBy"))) {
            log.info("updateBy is null");
            this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
        }
    }
}
