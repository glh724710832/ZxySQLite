package com.ellen.zxysqlite.table.reflection;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ellen.zxysqlite.createsql.create.createtable.SQLField;
import com.ellen.zxysqlite.table.ZxyTable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类是基于反射对类进行自动建表
 * 完全类似于LitePal的用法
 */
public abstract class ZxyReflectionTable<T> extends ZxyTable {

    private Class dataClass;
    private List<SQLField> sqlFieldList;
    private String tableName;

    public ZxyReflectionTable(SQLiteDatabase db,Class<? extends T> dataClass) {
        super(db);
        this.dataClass = dataClass;
        this.tableName = dataClass.getSimpleName();
        getFields();
    }

    public ZxyReflectionTable(SQLiteDatabase db,Class<? extends T> dataClass,String autoTableName) {
        super(db);
        this.dataClass = dataClass;
        this.tableName = autoTableName;
        getFields();
    }

    /**
     * 通过反射获取类的所有属性
     */
    private void getFields(){
        sqlFieldList = new ArrayList<>();
        List<Field> fieldList = getClassFieldList(dataClass,false);
        for(Field field:fieldList) {
            String fieldType = getSQLFieldType(field.getName(),field.getType());
            String fieldName = getSQLFieldName(field.getName(),field.getType());
            SQLField sqlField = SQLField.getOrdinaryField(fieldName,fieldType);
            sqlFieldList.add(sqlField);
        }
    }

    public void onCreateTable(){
        String createTableSql =  getCreateTable()
                .setTableName(tableName)
                .addField(sqlFieldList)
                .createSQL();
        getSQLiteDatabase().execSQL(createTableSql);
    }

    public void onCreateTableIfNotExits(){
        String createTableSql =  getCreateTable()
                .setTableName(tableName)
                .addField(sqlFieldList)
                .createSQLIfNotExists();
        Log.e("创建表的SQL",createTableSql);
        getSQLiteDatabase().execSQL(createTableSql);
    }


    private List<Field> getClassFieldList(Class dataClass,boolean isAddStatic){
        List<Field> fieldList = new ArrayList<>();
        java.lang.reflect.Field[] fields = dataClass.getDeclaredFields();
        if(fields != null && fields.length != 0) {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                //过滤掉静态的字段
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if(!isStatic) {
                    fieldList.add(field);
                }else {
                    if(isAddStatic){
                        fieldList.add(field);
                    }
                }
            }
        }
        return fieldList;
    }

    /**
     * 根据字段的名字和类型返回相应的数据库中的保存类型
     * example：int -> Integer
     * @param classFieldName
     * @param typeClass
     * @return
     */
    public abstract String getSQLFieldType(String classFieldName,Class typeClass);
    /**
     * 根据字段的名字和类型返回相应的数据库中的保存类型
     * example：int -> Integer
     * @param classFieldName
     * @param typeClass
     * @return
     */
    public abstract String getSQLFieldName(String classFieldName,Class typeClass);

}
