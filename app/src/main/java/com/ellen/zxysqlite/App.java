package com.ellen.zxysqlite;

import android.app.Application;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("初始化应用","ok");
    }

    @Override
    public File getDatabasePath(String name) {
        Log.e("执行没","ok");
        File file = new File(Environment.getExternalStorageDirectory(),"ZxySQLite");
        if(!file.exists()){
            file.exists();
        }
        return file;
    }


    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        Log.e("执行没","ok");
        File file = new File(Environment.getExternalStorageDirectory(),"ZxySQLite");
        if(!file.exists()){
            file.mkdirs();
        }
        return SQLiteDatabase.openOrCreateDatabase(file.getAbsolutePath()+"/",null);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        Log.e("执行没","ok");
        File file = new File(Environment.getExternalStorageDirectory(),"ZxySQLite");
        if(!file.exists()){
            file.mkdirs();
        }
        return SQLiteDatabase.openOrCreateDatabase(file.getAbsolutePath()+"/",null);
    }

}
