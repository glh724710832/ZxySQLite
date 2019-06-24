package com.ellen.zxysqlite.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Where{

    private Map<String,WhereValue> whereValueMap;

    public static Where getInstance(){
        Where where = new Where();
        where.whereValueMap = new HashMap<>();
        return where;
    }

    public Where addEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,"=",value);
    }

    public Where addMoreThanTheWhereValue(String whereName, Object value){
        return addWherValue(whereName,">",value);
    }

    public Where addMoreThanTheEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,">=",value);
    }

    public Where addLessThanWhereValue(String whereName, Object value){
        return addWherValue(whereName,"<",value);
    }

    public Where addLessThanEqualWhereValue(String whereName, Object value){
        return addWherValue(whereName,"<=",value);
    }

    public  Where addWherValue(String whereName, String whereFuHao, Object value){
        WhereValue whereValue = new WhereValue(whereName,whereFuHao,value);
        whereValueMap.put(whereName,whereValue);
        return this;
    }

    public String getWhereSQLString(Map<String,WhereValue> whereMap){
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> columnNameSet = whereMap.keySet();
        int i =0;
        for(String columnName:columnNameSet){
            i++;
            WhereValue whereValue = whereMap.get(columnName);
            stringBuilder.append(columnName+whereValue.getWhereSymbol());
            if(whereValue.getValue() instanceof String){
                stringBuilder.append("'"+whereValue.getValue()+"'");
            }else {
                stringBuilder.append(whereValue.getValue());
            }
            if(i != whereMap.size()){
                stringBuilder.append(" AND ");
            }
        }
        return stringBuilder.toString();
    }

}
