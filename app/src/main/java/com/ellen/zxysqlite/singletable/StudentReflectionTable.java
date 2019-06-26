package com.ellen.zxysqlite.singletable;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.Student;
import com.ellen.zxysqlite.table.reflection.ZxyReflectionTable;

public class StudentReflectionTable extends ZxyReflectionTable<Student> {


    public StudentReflectionTable(SQLiteDatabase db, Class<? extends Student> dataClass) {
        super(db, dataClass);
    }

    public StudentReflectionTable(SQLiteDatabase db, Class<? extends Student> dataClass, String autoTableName) {
        super(db, dataClass, autoTableName);
    }

    @Override
    public String getSQLFieldType(String classFieldName, Class typeClass) {
        return getSQlStringType(typeClass);
    }

    @Override
    public String getSQLFieldName(String classFieldName, Class typeClass) {
        return classFieldName;
    }

    @Override
    protected Object setBooleanValue(String classFieldName, boolean value) {
        if(value){
            return "1";
        }else {
            return "0";
        }
    }

    @Override
    protected String setBooleanSQLDataType(String classFieldName) {
        return "text";
    }


}
