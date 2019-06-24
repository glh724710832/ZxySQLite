package com.ellen.zxy.update;

import com.ellen.ellensqlite.helper.BaseSql;
import com.ellen.ellensqlite.helper.WhereValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新表的数据
 */
public class UpdateTableDataRow extends BaseSql {

    private String tableName;
    private Map<String,Object> setValueMap;
    private Map<String, WhereValue> whereMap;

    public static UpdateTableDataRow getInstance(){
        UpdateTableDataRow updateTableColumn = new UpdateTableDataRow();
        updateTableColumn.setValueMap = new HashMap<>();
        updateTableColumn.whereMap = new HashMap<>();
        return updateTableColumn;
    }

    public UpdateTableDataRow addTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public UpdateTableDataRow addSetValue(String columnName, Object value){
        setValueMap.put(columnName,value);
        return this;
    }

    public UpdateTableDataRow addEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,"=",value);
    }

    public UpdateTableDataRow addNoEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,"!=",value);
    }

    public UpdateTableDataRow addMoreThanTheWhereValue(String whereName, Object value){
        return addWherValue(whereName,">",value);
    }

    public UpdateTableDataRow addMoreThanTheEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,">=",value);
    }

    public UpdateTableDataRow addLessThanWhereValue(String whereName, Object value){
        return addWherValue(whereName,"<",value);
    }

    public UpdateTableDataRow addLessThanEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,"<=",value);
    }

    private UpdateTableDataRow addWherValue(String whereName, String whereFuHao, Object value){
        WhereValue whereValue = new WhereValue(whereName,whereFuHao,value);
        whereMap.put(whereName,whereValue);
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE "+tableName);
        stringBuilder.append(" SET ");
        stringBuilder.append(getSetValueSQLString(setValueMap));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(getWhereSQLString(whereMap)+";");
        return stringBuilder.toString();
    }

}
