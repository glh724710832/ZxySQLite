package com.ellen.zxysqlite.table;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.createsql.create.createtable.CreateTable;
import com.ellen.zxysqlite.createsql.create.createtable.SQLField;

import java.util.List;

/**
 * 多表支持类，表的所有字段一样
 */
public abstract class ZxyManyTable<T> extends ZxyTable {

    public ZxyManyTable(SQLiteDatabase db) {
        super(db);
        onCreateTables();
    }

    protected abstract String[] getTableNames();
    //返回表的字段集合
    protected abstract List<SQLField> getCreateSQLFields();
    protected abstract boolean addData(String tableName,T data);
    protected abstract boolean addDatas(String tableName,List<T> dataList);

    private void onCreateTables(){
        List<SQLField> SQLFieldList = getCreateSQLFields();
        String[] tableNames = getTableNames();
        for(int n=0;n<tableNames.length;n++) {
            CreateTable createTable = CreateTable.getInstance().setTableName(tableNames[n]);
            for (int i = 0; i < SQLFieldList.size(); i++) {
                createTable.addField(SQLFieldList.get(i));
            }
            String createTableSQL = createTable.createSQLIfNotExists();
            exeSQL(createTableSQL);
        }
    }

}
