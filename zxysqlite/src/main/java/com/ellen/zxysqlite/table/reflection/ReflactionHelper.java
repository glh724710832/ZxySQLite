package com.ellen.zxysqlite.table.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflactionHelper<T> {

    public List<Field> getClassFieldList(Class<? extends T> dataClass, boolean isAddStatic){
        List<Field> fieldList = new ArrayList<>();
        java.lang.reflect.Field[] fields = dataClass.getDeclaredFields();
        if(fields != null && fields.length != 0) {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                //过滤掉静态的字段
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if(!isStatic) {
                    fieldList.add(field);
                }else {
                    if(isAddStatic){
                        fieldList.add(field);
                    }
                }
            }
        }
        return fieldList;
    }

    public Object getValue(T t, Field targetField) {
        targetField.setAccessible(true);
        Object value = null;
        try {
            value = targetField.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

}
