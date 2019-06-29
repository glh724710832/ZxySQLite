package com.ellen.zxysqlite;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.createsql.helper.SQLFieldType;
import com.ellen.zxysqlite.createsql.helper.SQLFieldTypeEnum;
import com.ellen.zxysqlite.table.reflection.EncryptionInterFace;
import com.ellen.zxysqlite.table.reflection.ZxyReflectionTable;

public class PhoneSQLite extends ZxyReflectionTable<Phone> implements EncryptionInterFace {

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

    @Override
    public Object encryption(String classFieldName, String sqlFieldName, Class typeClass, Object value) {
        if(classFieldName.equals("xinHao")){
            String str = (String) value;
            str = "加密"+str;
            return str;
        }
        return value;
    }

    @Override
    public Object decrypt(String classFieldName, String sqlFieldName, Class typeClass, Object value) {
        if(classFieldName.equals("xinHao")){
            String str = (String) value;
            str = str.substring(2);
            return str;
        }
        return value;
    }
}
