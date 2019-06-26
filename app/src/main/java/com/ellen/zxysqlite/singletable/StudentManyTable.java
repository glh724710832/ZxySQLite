package com.ellen.zxysqlite.singletable;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.Student;
import com.ellen.zxysqlite.createsql.create.createtable.SQLField;
import com.ellen.zxysqlite.table.ZxyManyTable;

import java.util.List;

public class StudentManyTable extends ZxyManyTable<Student> {

    public StudentManyTable(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected String[] getTableNames() {
        return new String[0];
    }

    @Override
    protected List<SQLField> getCreateSQLFields() {
        return null;
    }

    @Override
    protected boolean addData(String tableName, Student data) {
        return false;
    }

    @Override
    protected boolean addDatas(String tableName, List<Student> dataList) {
        return false;
    }
}
