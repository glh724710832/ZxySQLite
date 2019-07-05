package com.ellen.zxysqlite;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.sqlitecreate.createsql.helper.SQLFieldType;
import com.ellen.sqlitecreate.createsql.helper.SQLFieldTypeEnum;
import com.ellen.zxysqlite.table.reflection.ZxyReflectionTable;

public class PhoneSQLite extends ZxyReflectionTable<Phone>{

    public PhoneSQLite(SQLiteDatabase db, Class<? extends Phone> dataClass) {
        super(db, dataClass);
    }

    @Override
    protected SQLFieldType getSQLFieldType(String classFieldName, Class typeClass) {
        if(typeClass == Boolean.class || typeClass.getName().equals("boolean")){
            return new SQLFieldType(SQLFieldTypeEnum.TEXT,5);
        }else {
            return new SQLFieldType(getSQlStringType(typeClass), null);
        }
    }

    @Override
    protected String getSQLFieldName(String classFieldName, Class typeClass) {
        return classFieldName;
    }

    @Override
    protected Object setBooleanValue(String classFieldName, boolean value) {
        if(value){
            return "true";
        }else {
            return "false";
        }
    }

    @Override
    protected SQLFieldType conversionSQLiteType(String classFieldName, Class typeClass) {
        SQLFieldType sqlFieldType = new SQLFieldType(SQLFieldTypeEnum.TEXT,null);
        return sqlFieldType;
    }

    @Override
    protected Object setConversionValue(Phone phone, String className, Class typeClass) {
        return phone.getSystem().getName();
    }

    @Override
    protected Object resumeConversionObject(Object value, String className, Class typeClass) {
        System system = new System((String) value,2);
        return system;
    }
    
}
