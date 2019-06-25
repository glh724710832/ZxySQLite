package com.ellen.zxysqlite.add;


import com.ellen.zxysqlite.create.createtable.Field;
import com.ellen.zxysqlite.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加多行数据
 *
 * example:
 * INSERT INTO student (id,name,sex) VALUES (3,'李三'，'男'),(4,'王五','女');
 *
 */
public class AddManyRowToTable extends BaseSql {
    private String tableName;
    private List<String> fieldList;
    private List<List<String>> listList;

    public static AddManyRowToTable getInstance() {
        AddManyRowToTable addManyRowToTable = new AddManyRowToTable();
        addManyRowToTable.fieldList = new ArrayList<>();
        addManyRowToTable.listList = new ArrayList<>();
        return addManyRowToTable;
    }

    public AddManyRowToTable setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public AddManyRowToTable addFieldNameList(List<String> fieldList) {
        this.fieldList.addAll(fieldList);
        return this;
    }

    public AddManyRowToTable addFieldList(List<Field> fieldList) {
        for(Field field:fieldList){
            this.fieldList.add(field.getName());
        }
        return this;
    }

    public AddManyRowToTable addValueList(List<Object> valueList) {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < valueList.size(); i++) {
            if(valueList.get(i) instanceof String) {
                String value = (String) valueList.get(i);
                values.add("'"+value+"'");
            }else {
                values.add(valueList.get(i).toString());
            }
        }
        listList.add(values);
        return this;
    }

    public String createSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO " + tableName + " ");
        stringBuilder.append("(");
        stringBuilder.append(getStringSQL(fieldList));
        stringBuilder.append(") ");
        stringBuilder.append("VALUES");
        for (int i = 0; i < listList.size(); i++) {
            stringBuilder.append("(");
            stringBuilder.append(getStringSQL(listList.get(i)));
            stringBuilder.append(")");
            if (i != listList.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
}
