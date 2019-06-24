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

    public static Field getOrdinaryField(String name,String fieldType){
        return new Field(name,fieldType);
    }

    public static Field getAutoEndStringField(String name,String fieldType,String autoEndString){
        return new Field(name,fieldType,autoEndString);
    }

    public static Field getPrimaryKeyField(String name,String fieldType,boolean isAutoInCrement){
        return new Field(name,fieldType,true,isAutoInCrement);
    }

    public static Field getContainsDefaultValueField(String name,String fieldType,Object defaultValue){
        return new Field(name,fieldType,false,defaultValue);
    }

    public static Field getNotNullValueField(String name,String fieldType){
        return new Field(name,fieldType,false,null);
    }

    public static Field getNotNullContainsDefaultValueField(String name,String fieldType,Object defaultValue){
        return new Field(name,fieldType,false,defaultValue);
    }

    /**
     * 普通，啥都不能设置
     * @param name
     * @param filedType
     */
    private Field(String name, String filedType) {
        this.name = name;
        this.filedType = filedType;
    }

    /**
     * 自定义尾部
     * @param name
     * @param filedType
     * @param autoEndString
     */
    private Field(String name, String filedType, String autoEndString) {
        this.name = name;
        this.filedType = filedType;
        this.autoEndString = autoEndString;
    }

    /**
     *
     * @param name 列名
     * @param filedType 列在SQL中的数据类型
     * @param isPrimaryKey 是否为主键
     * @param isAutoInCrement 是否自增
     */
    private Field(String name,String filedType,boolean isPrimaryKey,boolean isAutoInCrement){
        StringBuilder stringBuilder = new StringBuilder();
        if(isPrimaryKey){
            stringBuilder.append(" PRIMARY KEY");
        }
        if(isAutoInCrement){
            stringBuilder.append(" AUTOINCREMENT");
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
    private Field(String name,String filedType,boolean isCanNull,Object defaultValue){
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
