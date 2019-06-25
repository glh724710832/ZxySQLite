package com.ellen.zxysqlite.table;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.ZxySQLite;

public class ZxyTable extends ZxySQLite {
    private SQLiteDatabase db;

    public ZxyTable(SQLiteDatabase db){
        this.db = db;
    }

    public SQLiteDatabase getSQliteDatabase(){
        return db;
    }

    public void exeSQL(String sql){
        getSQliteDatabase().execSQL(sql);
    }

    public Cursor serachBySQL(String sql){
        return getSQliteDatabase().rawQuery(sql,null);
    }

}
