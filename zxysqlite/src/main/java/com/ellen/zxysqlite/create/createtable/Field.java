package com.ellen.zxysqlite.create.createtable;

/**
 * 用于创建表的字段（列）
 */
public class Field {
    //字段的名称
    private String name;
    //字段的数据类型
    private String filedType;
    //字段的末尾设置，例如:主键，不为NULL,默认值
    private String autoEndString;

    /**
     * 自定义尾部
     * @param name
     * @param filedType
     * @param autoEndString
     */
    public Field(String name, String filedType, String autoEndString) {
        this.name = name;
        this.filedType = filedType;
        this.autoEndString = autoEndString;
    }

    /**
     * 主键
     * @param name
     * @param filedType
     * @param isPrimaryKey
     */
    public Field(String name,String filedType,boolean isPrimaryKey){
        StringBuilder stringBuilder = new StringBuilder();
        if(isPrimaryKey){
            stringBuilder.append(" PRIMARY KEY");
        }
        this.name = name;
        this.filedType = filedType;
        if(stringBuilder.toString().length() > 0)
        this.autoEndString = stringBuilder.toString();
    }

    /**
     * 非主键
     * @param name
     * @param filedType
     * @param isCanNull 是否能为NULL
     * @param defaultValue 默认值
     */
    public Field(String name,String filedType,boolean isCanNull,Object defaultValue){
        StringBuilder stringBuilder = new StringBuilder();
        if(defaultValue != null){
            stringBuilder.append(" DEFAULT ");
            if(defaultValue instanceof String){
                stringBuilder.append("'"+defaultValue+"'");
            }else {
                stringBuilder.append(defaultValue);
            }
        }
        if(!isCanNull){
            stringBuilder.append(" NOT NULL");
        }
        this.name = name;
        this.filedType = filedType;
        if(stringBuilder.toString().length() > 0)
            this.autoEndString = stringBuilder.toString();
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

    public String getAutoEndString() {
        return autoEndString;
    }

    public void setAutoEndString(String autoEndString) {
        this.autoEndString = autoEndString;
    }
}
