package com.ellen.zxysqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.create.createtable.CreateTable;
import com.ellen.zxysqlite.create.createtable.Field;
import com.ellen.zxysqlite.delete.DeleteTable;
import com.ellen.zxysqlite.serach.SerachTableExist;

import java.util.List;

/**
 * 自由行高
 * @param <T> 要操作的表对应的类
 */
public abstract class ZxySingleTable<T> extends ZxySQLite{

    protected SQLiteDatabase db;

    public SQLiteDatabase getSQLiteDatabase() {
        return db;
    }

    public ZxySingleTable(SQLiteDatabase db){
        this.db = db;
        onCreateTable();
    }

    //返回表的名字
    protected abstract String getTableName();
    //返回表的字段集合
    protected abstract List<Field> getCreateSQLFields();

    //增加数据(单条)
    protected abstract boolean addData(T data);
    //增加数据(多条)
    protected abstract boolean addData(List<T> datas);
    //删除数据
    protected abstract boolean deleteData(String deleteSQL);
    //修改数据
    protected abstract boolean updateData(String whereSQL,T t);
    //查询数据
    protected abstract List<T> serachData(String whereSQL);

    private void onCreateTable() {
        //先判断表之前是否已经创建
        Cursor cursor = db.rawQuery(SerachTableExist.getInstance().
                setTableName(getTableName()).createSQL(), null);
        String tableName = null;
        while (cursor.moveToNext()) {
            tableName = cursor.getString(0);
        }
        //表之前不存在，才会选择去创建表
        if (tableName == null) {
            List<Field> fieldList = getCreateSQLFields();
            CreateTable createTable = CreateTable.getInstance().setTableName(getTableName());
            for (int i = 0; i < fieldList.size(); i++) {
                createTable.addField(fieldList.get(i));
            }
            String createTableSQL = createTable.createSQL();
            db.execSQL(createTableSQL);
        }
    }

}
