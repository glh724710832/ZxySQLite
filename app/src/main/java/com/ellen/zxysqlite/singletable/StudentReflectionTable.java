package com.ellen.zxysqlite.singletable;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.sqlitecreate.createsql.helper.SQLFieldType;
import com.ellen.sqlitecreate.createsql.helper.SQLFieldTypeEnum;
import com.ellen.zxysqlite.Student;
import com.ellen.zxysqlite.Teacher;
import com.ellen.zxysqlite.table.reflection.ZxyReflectionTable;
import com.google.gson.Gson;

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

    @Override
    protected SQLFieldType conversionSQLiteType(String classFieldName, Class typeClass) {
        return new SQLFieldType(SQLFieldTypeEnum.TEXT,null);
    }

    @Override
    protected Object setConversionValue(Student student, String className, Class typeClass) {
        if(className.equals("teacher")) {
            Gson gson = new Gson();
            return gson.toJson(student.getTeacher());
        }else {
            Gson gson = new Gson();
            return gson.toJson(student.getProjectName());
        }
    }

    @Override
    protected Object resumeConversionObject(Object value, String className, Class typeClass) {
        if(className.equals("teacher")) {
            String json = (String) value;
            Teacher teacher = new Gson().fromJson(json, Teacher.class);
            return teacher;
        }else {
            String json = (String) value;
            String[] strs = new Gson().fromJson(json,String[].class);
            return strs;
        }
    }

}
