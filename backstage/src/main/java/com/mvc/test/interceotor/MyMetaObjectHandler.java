package com.mvc.test.interceotor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
//    实现字段自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("created_at", new Date(), metaObject);
//        this.setFieldValByName("updated_at", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updated_at", new Date(), metaObject);
    }
}
