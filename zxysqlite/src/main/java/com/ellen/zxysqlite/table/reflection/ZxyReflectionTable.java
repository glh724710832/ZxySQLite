package com.ellen.zxysqlite.table.reflection;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ellen.zxysqlite.createsql.add.AddManyRowToTable;
import com.ellen.zxysqlite.createsql.add.AddSingleRowToTable;
import com.ellen.zxysqlite.createsql.create.createtable.SQLField;
import com.ellen.zxysqlite.createsql.helper.SQLFieldType;
import com.ellen.zxysqlite.createsql.helper.SQLFieldTypeEnum;
import com.ellen.zxysqlite.createsql.helper.Value;
import com.ellen.zxysqlite.createsql.serach.SerachTableData;
import com.ellen.zxysqlite.createsql.update.UpdateTableDataRow;
import com.ellen.zxysqlite.table.ZxyTable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 此类是基于反射对类进行自动建表
 * 完全类似于LitePal的用法
 */
public abstract class ZxyReflectionTable<T> extends ZxyTable {

    private Class dataClass;
    private List<SQLField> sqlFieldList;
    private String tableName;
    private ReflactionHelper<T> reflactionHelper;
    private HashMap<SQLField,Field> sqlNameMap;

    public ZxyReflectionTable(SQLiteDatabase db,Class<? extends T> dataClass) {
        super(db);
        this.dataClass = dataClass;
        this.tableName = dataClass.getSimpleName();
        reflactionHelper = new ReflactionHelper<>();
        sqlNameMap = new HashMap<>();
        getFields();
    }

    public ZxyReflectionTable(SQLiteDatabase db,Class<? extends T> dataClass,String autoTableName) {
        super(db);
        this.dataClass = dataClass;
        this.tableName = autoTableName;
        reflactionHelper = new ReflactionHelper<>();
        sqlNameMap = new HashMap<>();
        getFields();
    }

    /**
     * 通过反射获取类的所有属性
     */
    private void getFields(){
        sqlFieldList = new ArrayList<>();
        List<Field> fieldList = reflactionHelper.getClassFieldList(dataClass,false);
        for(Field field:fieldList) {
            String fieldType = getSQLFieldType(field.getName(),field.getType()).getSQLFieldTypeString();
            String fieldName = getSQLFieldName(field.getName(),field.getType());
            SQLField sqlField = SQLField.getOrdinaryField(fieldName,fieldType);
            sqlNameMap.put(sqlField,field);
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

        getSQLiteDatabase().execSQL(createTableSql);
    }

    public void onCreateTable(OnCreateSQLiteCallback onCreateSQLiteCallback){
        boolean isException = false;
        String createTableSql =  getCreateTable()
                .setTableName(tableName)
                .addField(sqlFieldList)
                .createSQL();
        onCreateSQLiteCallback.onCreateTableBefore(tableName,sqlFieldList,createTableSql);
        try {
            getSQLiteDatabase().execSQL(createTableSql);
        }catch (SQLException e){
            onCreateSQLiteCallback.onCreateTableFailure(e.getMessage(),tableName,sqlFieldList,createTableSql);
            isException = true;
        }
        if(!isException)
        onCreateSQLiteCallback.onCreateTableSuccess(tableName,sqlFieldList,createTableSql);
    }

    public void onCreateTableIfNotExits(OnCreateSQLiteCallback onCreateSQLiteCallback){
        boolean isException = false;
        String createTableSql =  getCreateTable()
                .setTableName(tableName)
                .addField(sqlFieldList)
                .createSQLIfNotExists();
        onCreateSQLiteCallback.onCreateTableBefore(tableName,sqlFieldList,createTableSql);
        try {
            getSQLiteDatabase().execSQL(createTableSql);
        }catch (SQLException e){
            onCreateSQLiteCallback.onCreateTableFailure(e.getMessage(),tableName,sqlFieldList,createTableSql);
            isException = true;
        }
        if(!isException)
        onCreateSQLiteCallback.onCreateTableSuccess(tableName,sqlFieldList,createTableSql);
    }

    /**
     * 重命名表
     */
    public void reNameTable(String newName){
        boolean isException = false;
        String reNameTableSql = getUpdateTableName()
                .setOldTableName(tableName)
                .setNewTableName(newName)
                .createSQL();
        try {
            exeSQL(reNameTableSql);
        }catch (SQLException e){
            isException = true;
        }
        if(!isException){
            this.tableName = newName;
        }
    }

    /**
     * 重命名表
     * @param newName
     * @param onRenameTableCallbcak
     */
    public void reNameTable(String newName,OnRenameTableCallbcak onRenameTableCallbcak){
        boolean isException = false;
        String reNameTableSql = getUpdateTableName()
                .setOldTableName(tableName)
                .setNewTableName(newName)
                .createSQL();
        try {
            exeSQL(reNameTableSql);
        }catch (SQLException e){
           isException = true;
           onRenameTableCallbcak.onRenameFailure(e.getMessage(),tableName,newName,reNameTableSql);
        }
        if(!isException){
            this.tableName = newName;
            onRenameTableCallbcak.onRenameSuccess(tableName,newName,reNameTableSql);
        }
    }

    public void deleteTable(String tableName){
        String deleteTableSQL = getDeleteTable().setTableName(tableName).createSQL();
        exeSQL(deleteTableSQL);
    }

    public void deleteTable(String tableName,OnDeleteTableCallback onDeleteTableCallback){
        boolean isException = false;
        String deleteTableSQL = getDeleteTable().setTableName(tableName).createSQL();
        try {
            exeSQL(deleteTableSQL);
        }catch (Exception e){
            isException = true;
            onDeleteTableCallback.onDeleteTableFailure(e.getMessage(),deleteTableSQL);
        }
        if(!isException){
            onDeleteTableCallback.onDeleteTableSuccess(deleteTableSQL);
        }
    }

    /**
     * 保存数据(单条)
     * @param data
     */
    public void saveData(T data){
        AddSingleRowToTable addSingleRowToTable = getAddSingleRowToTable();
        addSingleRowToTable.setTableName(tableName);
        for(int i = 0;i<sqlFieldList.size();i++){
            Field field = sqlNameMap.get(sqlFieldList.get(i));
            Object value = reflactionHelper.getValue(data,field);
            if(value instanceof Boolean){
                value = setBooleanValue(field.getName(),(Boolean) value);
            }
            addSingleRowToTable.addData(new Value(sqlFieldList.get(i).getName(),value));
        }
        String addDataSql = addSingleRowToTable.createSQL();
        exeSQL(addDataSql);
    }

    /**
     * 保存数据(多条)
     * @param dataList
     */
    public void saveData(List<T> dataList){
        AddManyRowToTable addManyRowToTable = getAddManyRowToTable();
        addManyRowToTable.setTableName(tableName);
        addManyRowToTable.addFieldList(sqlFieldList);
        for(int i=0;i<dataList.size();i++){
            List list = new ArrayList();
            for(int j =0;j<sqlFieldList.size();j++) {
                Field field = sqlNameMap.get(sqlFieldList.get(j));
                Object value = reflactionHelper.getValue(dataList.get(i), field);
                if (value instanceof Boolean) {
                    value = setBooleanValue(field.getName(),(Boolean) value);
                }
                list.add(value);
            }
            addManyRowToTable.addValueList(list);
        }
        String addDataSql = addManyRowToTable.createSQL();
        exeSQL(addDataSql);
    }

    /**
     * 删除
     * 建议使用Where系列类生产Where SQL语句
     * @param whereSQL
     */
    public void delete(String whereSQL){
        String deleteSQL = getDeleteTableDataRow().setTableName(tableName).createSQLAutoWhere(whereSQL);
        exeSQL(deleteSQL);
    }

    /**
     * 清空数据
     */
    public void clear(){
        String clearTableSQL = getDeleteTableDataRow().setTableName(tableName).createDeleteAllDataSQL();
        exeSQL(clearTableSQL);
    }

    /**
     * 修改数据
     * 建议使用Where系列类生产Where SQL语句
     * @param t
     * @param whereSQL
     */
    public void update(T t,String whereSQL){
        UpdateTableDataRow updateTableDataRow = getUpdateTableDataRow();
        updateTableDataRow.setTableName(tableName);
        for(int i=0;i<sqlFieldList.size();i++){
            String fieldName = sqlFieldList.get(i).getName();
            Field field = sqlNameMap.get(sqlFieldList.get(i));
            Object value = reflactionHelper.getValue(t,field);
            if(value instanceof Boolean){
                value = setBooleanValue(field.getName(),(Boolean) value);
            }
            updateTableDataRow.addSetValue(fieldName,value);
        }
        String updateSql = updateTableDataRow.createSQLAutoWhere(whereSQL);
        exeSQL(updateSql);
    }

    /**
     * 获取表中所有数据
     * @param orderSQL
     * @return
     */
    public List<T> getAllDatas(String orderSQL){
        SerachTableData serachTableData = getSerachTableData().setTableName(tableName);
        serachTableData.setIsAddField(false);
        String getAllTableDataSQL = serachTableData.getTableAllDataSQL(orderSQL);
        Log.e("查询语句",getAllTableDataSQL);
        return serachDatasBySQL(getAllTableDataSQL);
    }

    public List<T> serach(String whereSQL,String orderSQL){
        List<T> dataList = new ArrayList<>();
        SerachTableData serachTableData = getSerachTableData().setTableName(tableName);
        serachTableData.setIsAddField(false);
        String serachSQL = null;
        if(orderSQL != null) {
             serachSQL = serachTableData.createSQLAutoWhere(whereSQL, orderSQL);
        }else {
            serachSQL = serachTableData.createSQLAutoWhere(whereSQL);
        }
        Log.e("查询语句",serachSQL);
        return serachDatasBySQL(serachSQL);
    }

    private List<T> serachDatasBySQL(String sql){
        List<T> dataList = new ArrayList<>();
        Cursor cursor = serachBySQL(sql);
        while (cursor.moveToNext()){
            T t = null;
            Constructor constructors = null;
            try {
                constructors = dataClass.getConstructor();
                constructors.setAccessible(true);
                t = (T) constructors.newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            for(int i=0;i<sqlFieldList.size();i++) {
                Field field = sqlNameMap.get(sqlFieldList.get(i));
                int index = cursor.getColumnIndex(sqlFieldList.get(i).getName());
                String sqlDataType = getSQLFieldType(field.getName(),field.getType()).getTypeString();
                Class type = field.getType();
                Object value = null;
                if(sqlDataType.equals(SQLFieldTypeEnum.INTEGER.getTypeName())){
                    value = cursor.getInt(index);
                }else if(sqlDataType.equals(SQLFieldTypeEnum.BIG_INT.getTypeName())){
                    value = cursor.getLong(index);
                }else if(sqlDataType.equals(SQLFieldTypeEnum.REAL.getTypeName())){
                    if (type == Float.class || type.getName().equals("float")) {
                        value = cursor.getFloat(index);
                    } else if (type == Double.class || type.getName().equals("double")) {
                        value = cursor.getDouble(index);
                    }
                }else if(sqlDataType.equals(SQLFieldTypeEnum.TEXT.getTypeName())){
                    if (type == Character.class || type.getName().equals("char")) {
                        value = cursor.getString(index).charAt(0);
                    } else  {
                        value = cursor.getString(index);
                    }
                }else if(sqlDataType.equals(SQLFieldTypeEnum.BLOB.getTypeName())){

                }else if(sqlDataType.equals(SQLFieldTypeEnum.DATE.getTypeName())){
                       value = cursor.getString(index);
                }else if(sqlDataType.equals(SQLFieldTypeEnum.NUMERIC.getTypeName())){

                }

                try {
                    if(field.getType() == Boolean.class || field.getType().getName().equals("boolean")) {
                        Object booleanTrueValue = setBooleanValue(field.getName(),true);
                        if(booleanTrueValue.equals(value)){
                            field.set(t, true);
                        }else {
                            field.set(t,false);
                        }
                    }else {
                        field.set(t, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            dataList.add(t);
        }
        if(cursor != null){
            cursor.close();
        }
        return dataList;
    }

    /**
     * 根据字段的名字和类型返回相应的数据库中的保存类型
     * example：int -> Integer
     * @param classFieldName
     * @param typeClass
     * @return
     */
    protected abstract SQLFieldType getSQLFieldType(String classFieldName, Class typeClass);
    /**
     * 根据字段的名字和类型返回相应的数据库中的保存类型
     * example：int -> Integer
     * @param classFieldName
     * @param typeClass
     * @return
     */
    protected abstract String getSQLFieldName(String classFieldName,Class typeClass);
    protected abstract Object setBooleanValue(String classFieldName,boolean value);

    public SQLFieldTypeEnum getSQlStringType(Class<?> ziDuanJavaType) {
        SQLFieldTypeEnum sqlType = null;
        if (ziDuanJavaType == Byte.class || ziDuanJavaType.getName().equals("byte")) {
            sqlType = SQLFieldTypeEnum.INTEGER;
        } else if (ziDuanJavaType == Short.class || ziDuanJavaType.getName().equals("short")) {
            sqlType = SQLFieldTypeEnum.INTEGER;
        } else if (ziDuanJavaType == Integer.class || ziDuanJavaType.getName().equals("int")) {
            sqlType = SQLFieldTypeEnum.INTEGER;
        } else if (ziDuanJavaType == Long.class || ziDuanJavaType.getName().equals("long")) {
            sqlType = SQLFieldTypeEnum.BIG_INT;
        } else if (ziDuanJavaType == Float.class || ziDuanJavaType.getName().equals("float")) {
            sqlType = SQLFieldTypeEnum.REAL;
        } else if (ziDuanJavaType == Double.class || ziDuanJavaType.getName().equals("double")) {
            sqlType = SQLFieldTypeEnum.REAL;
        } else if (ziDuanJavaType == Boolean.class || ziDuanJavaType.getName().equals("boolean")) {
            sqlType = SQLFieldTypeEnum.INTEGER;
        } else if (ziDuanJavaType == Character.class || ziDuanJavaType.getName().equals("char")) {
            sqlType = SQLFieldTypeEnum.TEXT;
        } else if (ziDuanJavaType == String.class) {
            sqlType = SQLFieldTypeEnum.TEXT;
        } else {
            sqlType = SQLFieldTypeEnum.TEXT;
        }
        return sqlType;
    }

    public interface OnCreateSQLiteCallback{
        void onCreateTableBefore(String tableName,List<SQLField> sqlFieldList,String createSQL);
        void onCreateTableFailure(String errMessage,String tableName,List<SQLField> sqlFieldList,String createSQL);
        void onCreateTableSuccess(String tableName,List<SQLField> sqlFieldList,String createSQL);
    }

    public interface OnRenameTableCallbcak{
        void onRenameFailure(String errMessage,String currentName,String newName,String reNameTableSQL);
        void onRenameSuccess(String oldName,String newName,String reNameTableSQL);
    }

    public interface OnDeleteTableCallback{
        void onDeleteTableFailure(String errMessage,String deleteTableSQL);
        void onDeleteTableSuccess(String deleteTableSQL);
    }
}
