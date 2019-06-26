package com.ellen.zxysqlite.singletable;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.Student;
import com.ellen.zxysqlite.createsql.create.createtable.SQLField;
import com.ellen.zxysqlite.table.ZxySingleTable;

import java.util.List;

public class StudentTable extends ZxySingleTable<Student> {

    public StudentTable(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected List<SQLField> getCreateSQLFields() {
        return null;
    }

    @Override
    protected boolean addData(Student data) {
        return false;
    }

    @Override
    protected boolean addData(List<Student> datas) {
        return false;
    }
}
