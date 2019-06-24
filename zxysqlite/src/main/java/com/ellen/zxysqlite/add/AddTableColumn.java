package com.ellen.zxysqlite.add;

import com.ellen.zxysqlite.create.createtable.Field;

/**
 * 动态添加表的列
 */
public class AddTableColumn {
    private String tableName;
    private Field field;

    public static AddTableColumn getInstance(){
        return new AddTableColumn();
    }

    public AddTableColumn setTableColumn(String tableName){
        this.tableName = tableName;
        return this;
    }

    public AddTableColumn addField(Field field){
        this.field = field;
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALTER TABLE ");
        stringBuilder.append(tableName);
        stringBuilder.append(" ADD ");
        stringBuilder.append(field.getName()+" ");
        stringBuilder.append(field.getFiledType());
        if(field.getAutoEndString() != null)
        stringBuilder.append(" "+field.getAutoEndString());
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
}
