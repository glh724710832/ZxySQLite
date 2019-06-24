package com.ellen.zxysqlite.helper;

/**
 * 用于创建表的字段（列）
 */
public class Field {
    //字段的名称
    private String name;
    //字段的数据类型
    private String filedType;
    //字段的末尾设置，例如:主键，默认值等
    private String endString;

    public Field(String name, String filedType, String endString) {
        this.name = name;
        this.filedType = filedType;
        this.endString = endString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFiledType() {
        return filedType;
    }

    public void setFiledType(String filedType) {
        this.filedType = filedType;
    }

    public String getEndString() {
        return endString;
    }

    public void setEndString(String endString) {
        this.endString = endString;
    }
}
