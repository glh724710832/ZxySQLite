package com.ellen.zxysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.ellen.zxysqlite.table.reflection.ZxyLibrary;

public class MySQLiteHelper extends ZxyLibrary {


    public MySQLiteHelper(Context context, String name, int version) {
        super(context, name, version);
    }

    public MySQLiteHelper(Context context, String libraryPath, String name, int version) {
        super(context, libraryPath, name, version);
    }

    @Override
    public void onZxySQLiteCreate(SQLiteDatabase db) {

    }

    @Override
    public void onZxySQLiteUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
