package com.ellen.zxy.add;


import com.ellen.zxy.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

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

    public AddManyRowToTable addTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public AddManyRowToTable addFieldNameList(List<String> fieldList) {
        this.fieldList.addAll(fieldList);
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
