package com.ellen.zxy.add;

import com.ellen.zxy.helper.Field;

public class AddTableColumn {
    private String tableName;
    private Field field;

    public static AddTableColumn getInstance(){
        return new AddTableColumn();
    }

    public AddTableColumn addTableColumn(String tableName){
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
        if(field.getEndString() != null)
        stringBuilder.append(" "+field.getEndString());
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
}
