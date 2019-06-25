package com.ellen.zxysqlite.table;
import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.createsql.create.createtable.CreateTable;
import com.ellen.zxysqlite.createsql.create.createtable.Field;

import java.util.List;

/**
 * 自由性高，此类仅仅支持单表,多表请使用ZxyManyTable
 * 如果嫌弃它们太麻烦，请使用ZxyAutoTable类
 * @param <T> 要操作的表对应的类
 */
public abstract class ZxySingleTable<T> extends ZxyTable {

    public ZxySingleTable(SQLiteDatabase db) {
        super(db);
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
    protected abstract boolean updateData(String whereSQL, T t);

    //查询数据
    protected abstract List<T> serachData(String whereSQL);

    private void onCreateTable() {
        List<Field> fieldList = getCreateSQLFields();
        CreateTable createTable = CreateTable.getInstance().setTableName(getTableName());
        for (int i = 0; i < fieldList.size(); i++) {
            createTable.addField(fieldList.get(i));
        }
        String createTableSQL = createTable.createSQLIfNotExists();
        exeSQL(createTableSQL);
    }

}
