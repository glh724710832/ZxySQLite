package com.ellen.zxy.add;

import com.ellen.zxy.helper.BaseSql;
import com.ellen.zxy.helper.Value;

import java.util.ArrayList;
import java.util.List;

public class AddSingleRowToTable extends BaseSql {
    private String tableName;
    private List<Value> valueList;

    public static AddSingleRowToTable getInstanc(){
        AddSingleRowToTable addRowToTable = new AddSingleRowToTable();
        addRowToTable.valueList = new ArrayList<>();
        return addRowToTable;
    }

    public AddSingleRowToTable addTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public AddSingleRowToTable addData(Value value){
        valueList.add(value);
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(tableName+" ");
        stringBuilder.append("(");
        stringBuilder.append(getFieldNameStringByValue(valueList));
        stringBuilder.append(") ");
        stringBuilder.append("VALUES(");
        stringBuilder.append(getValueString(valueList));
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

}
