package com.ellen.zxysqlite.singletable;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.Student;
import com.ellen.zxysqlite.createsql.helper.SQLFieldType;
import com.ellen.zxysqlite.createsql.helper.SQLFieldTypeEnum;
import com.ellen.zxysqlite.table.reflection.ZxyReflectionTable;

public class StudentReflectionTable extends ZxyReflectionTable<Student> {


    public StudentReflectionTable(SQLiteDatabase db, Class<? extends Student> dataClass) {
        super(db, dataClass);
    }

    public StudentReflectionTable(SQLiteDatabase db, Class<? extends Student> dataClass, String autoTableName) {
        super(db, dataClass, autoTableName);
    }

    @Override
    protected SQLFieldType getSQLFieldType(String classFieldName, Class typeClass) {
        SQLFieldType sqlFieldType = null;
        if(classFieldName.equals("date")){
            sqlFieldType = new SQLFieldType(SQLFieldTypeEnum.DATE,null);
        }else if(classFieldName.equals("isMan")){
            sqlFieldType = new SQLFieldType(SQLFieldTypeEnum.TEXT,5);
        }else if(classFieldName.equals("nameOne")){
            sqlFieldType = new SQLFieldType(SQLFieldTypeEnum.TEXT,1);
        } else {
            sqlFieldType = new SQLFieldType(getSQlStringType(typeClass), null);
        }
        return sqlFieldType;
    }

    @Override
    public String getSQLFieldName(String classFieldName, Class typeClass) {
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

}
