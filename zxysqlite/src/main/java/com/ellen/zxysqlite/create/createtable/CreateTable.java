package com.ellen.zxysqlite.create.createtable;

import com.ellen.zxysqlite.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建表
 */
public class CreateTable extends BaseSql {

    private String tableName;
    private List<Field> fieldList;

    public static CreateTable getInstance() {
        CreateTable createTable = new CreateTable();
        createTable.fieldList = new ArrayList<>();
        return createTable;
    }

    public CreateTable setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public CreateTable addField(Field field) {
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

    public String createSQLByCheck() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS " + tableName + "(");
        stringBuilder.append(getFieldString(fieldList));
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

}
