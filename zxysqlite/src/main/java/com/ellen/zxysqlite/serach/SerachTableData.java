package com.ellen.zxysqlite.serach;

import com.ellen.zxysqlite.helper.BaseSql;
import com.ellen.zxysqlite.helper.WhereValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerachTableData extends BaseSql {
    private String tableName;
    private List<String> selectFieldNameList;
    private Map<String, WhereValue> whereValueMap;

    public static SerachTableData getInstance(){
        SerachTableData serachTableData  = new SerachTableData();
        serachTableData.selectFieldNameList = new ArrayList<>();
        serachTableData.whereValueMap = new HashMap<>();
        return serachTableData;
    }

    public SerachTableData addTableName(String tableName){
        this.tableName = tableName;
        return  this;
    }

    public SerachTableData addSelectField(String name){
        selectFieldNameList.add(name);
        return this;
    }

    public SerachTableData addEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,"=",value);
    }

    public SerachTableData addNoEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,"!=",value);
    }

    public SerachTableData addMoreThanTheWhereValue(String whereName, Object value){
        return addWherValue(whereName,">",value);
    }

    public SerachTableData addMoreThanTheEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,">=",value);
    }

    public SerachTableData addLessThanWhereValue(String whereName, Object value){
        return addWherValue(whereName,"<",value);
    }

    public SerachTableData addLessThanEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,"<=",value);
    }

    private SerachTableData addWherValue(String whereName, String whereFuHao, Object value){
        WhereValue whereValue = new WhereValue(whereName,whereFuHao,value);
        whereValueMap.put(whereName,whereValue);
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(getStringSQL(selectFieldNameList)+" ");
        stringBuilder.append("FROM "+tableName);
        if(whereValueMap.size() > 0) {
            stringBuilder.append(" WHERE " + getWhereSQLString(whereValueMap));
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public String createSQLAutoWhere(String whereSQL){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(getStringSQL(selectFieldNameList)+" ");
        stringBuilder.append("FROM "+tableName);
        stringBuilder.append(" WHERE " + whereSQL);
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

}
