package com.rayliu.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AutoFill implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {  // 添加的自动填充
        // createTime、updateTime实体类中属性名
        this.setFieldValByName("fromTime", new Date(), metaObject);
        this.setFieldValByName("toTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {  // 修改的自动填充
    }

}