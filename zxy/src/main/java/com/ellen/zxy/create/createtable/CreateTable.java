package com.ellen.zxy.create.createtable;

import com.ellen.ellensqlite.helper.BaseSql;
import com.ellen.ellensqlite.helper.Field;

import java.util.ArrayList;
import java.util.List;

public class CreateTable extends BaseSql {

    private String tableName;
    private List<Field> fieldList;

    public static CreateTable getInstance() {
        return new CreateTable();
    }

    public CreateTable tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public CreateTable addField(Field field) {
        if (fieldList == null) {
            fieldList = new ArrayList<>();
        }
        fieldList.add(field);
        return this;
    }

    public String createSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE " + tableName + "(");
        stringBuilder.append(getFieldString(fieldList));
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

}
