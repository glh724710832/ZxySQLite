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

    public String getSQlStringType(Class<?> ziDuanJavaType) {
        String sqlType = "";
        if (ziDuanJavaType == Byte.class || ziDuanJavaType.getName().equals("byte")) {
            sqlType = "integer";
        } else if (ziDuanJavaType == Short.class || ziDuanJavaType.getName().equals("short")) {
            sqlType = "integer";
        } else if (ziDuanJavaType == Integer.class || ziDuanJavaType.getName().equals("int")) {
            sqlType = "integer";
        } else if (ziDuanJavaType == Long.class || ziDuanJavaType.getName().equals("long")) {
            sqlType = "bigint";
        } else if (ziDuanJavaType == Float.class || ziDuanJavaType.getName().equals("float")) {
            sqlType = "real";
        } else if (ziDuanJavaType == Double.class || ziDuanJavaType.getName().equals("double")) {
            sqlType = "real";
        } else if (ziDuanJavaType == Boolean.class || ziDuanJavaType.getName().equals("boolean")) {
            sqlType = "integer";
        } else if (ziDuanJavaType == Character.class || ziDuanJavaType.getName().equals("char")) {
            sqlType = "text";
        } else if (ziDuanJavaType == String.class) {
            sqlType = "text";
        } else {
            sqlType = "no";
        }
        return sqlType;
    }

    @Override
    public String getSQLFieldType(String classFieldName, Class typeClass) {
        return getSQlStringType(typeClass);
    }

    @Override
    public String getSQLFieldName(String classFieldName, Class typeClass) {
        return classFieldName;
    }
}
