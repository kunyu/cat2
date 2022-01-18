package com.crazyloong.cat.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YPLI
 * @description  对数据库每条记录的创建时间和更新时间自动进行填充
 * @date 2022/1/12 0012 22:33
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        this.setFieldValByName("inputTime", df.format(new Date()), metaObject);
        this.setFieldValByName("updateTime", df.format(new Date()), metaObject);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        this.setFieldValByName("inputDate", df2.format(new Date()), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        this.setFieldValByName("updateTime", df.format(new Date()), metaObject);
    }
}
