package com.ellen.zxysqlite;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.createsql.helper.SQLFieldType;
import com.ellen.zxysqlite.createsql.helper.SQLFieldTypeEnum;
import com.ellen.zxysqlite.table.reflection.ZxyReflectionTable;
import com.google.gson.Gson;

public class PhoneSQLite extends ZxyReflectionTable<Phone> {

    public PhoneSQLite(SQLiteDatabase db, Class<? extends Phone> dataClass) {
        super(db, dataClass);
    }

    @Override
    protected SQLFieldType getSQLFieldType(String classFieldName, Class typeClass) {
        SQLFieldType sqlFieldType = new SQLFieldType(getSQlStringType(typeClass),null);
        return sqlFieldType;
    }

    @Override
    protected String getSQLFieldName(String classFieldName, Class typeClass) {
        return classFieldName;
    }

    @Override
    protected Object setBooleanValue(String classFieldName, boolean value) {
        return null;
    }

    @Override
    protected SQLFieldType conversion(String classFieldName, Class typeClass) {
        SQLFieldType sqlFieldType = new SQLFieldType(SQLFieldTypeEnum.TEXT,null);
        return sqlFieldType;
    }

    @Override
    protected String setConversionValue(Phone phone, String className, Class typeClass) {
        Gson gson = new Gson();
        return gson.toJson(phone.getSystem());
    }

    @Override
    protected System resumeConversionObject(Object value, String className, Class typeClass) {
        Gson gson = new Gson();
        return gson.fromJson((String) value,System.class);
    }
}
