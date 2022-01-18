package com.crazyloong.cat.util;

import com.crazyloong.cat.pojo.TushareData;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String value = dateFormat.format(date);
        gen.writeString(value);
    }

    /**
     * Tushare 返回的json数据转化成对象进行存储
     */
    public <T>  List<T> trunsferTushareData(Class<T> t,TushareData tushareData) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return trunsferTushareData(t,tushareData,null);
    }

    /**
     * Tushare 返回的json数据转化成对象进行存储
     * 其中通过snowflake 获取ID
     */
    public <T>  List<T> trunsferTushareData(Class<T> t,TushareData tushareData,String snowflakeName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        int size = tushareData.getItems().size();
        List<T> tList = new ArrayList<>(size);
        List<List<String>> items = tushareData.getItems();
        for (int i = 0; i < size; i++) {
            List<String> item = items.get(i);
            T t1 = t.getDeclaredConstructor().newInstance();
            List<String> fields = tushareData.getFields();
            for (int j = 0; j < fields.size(); j++) {
                String field = fields.get(j);
                Method method = t.getMethod("set"+field.substring(0,1).toUpperCase()+field.substring(1),String.class);
                method.invoke(t1,item.get(j));
            }
            if (snowflakeName != null) {
                Method method = t.getMethod("setId",String.class);
                method.invoke(t1,SpringContextUtil.getBean("snowflakeName",SnowflakeId.class).nextId());
            }
            tList.add(t1);
        }
        return tList;
    }

}
